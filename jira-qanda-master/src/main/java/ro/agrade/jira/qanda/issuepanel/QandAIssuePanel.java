package ro.agrade.jira.qanda.issuepanel;

import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.avatar.AvatarService;
import com.atlassian.jira.config.properties.ApplicationProperties;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.RendererManager;
import com.atlassian.jira.plugin.issuetabpanel.AbstractIssueTabPanel;
import com.atlassian.jira.plugin.issuetabpanel.IssueAction;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.security.PermissionManager;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.user.util.UserManager;
import com.atlassian.plugin.webresource.WebResourceManager;
import ro.agrade.jira.qanda.QandAService;
import ro.agrade.jira.qanda.Question;
import ro.agrade.jira.qanda.utils.JIRAUtils;
import ro.agrade.jira.qanda.utils.PermissionChecker;

import java.util.ArrayList;
import java.util.List;

/**
 * The issue panel
 */
public class QandAIssuePanel extends AbstractIssueTabPanel {
    private final WebResourceManager webResourceManager;
    private final PermissionManager permissionManager;
    private final JiraAuthenticationContext authContext;
    private final ApplicationProperties properties;
    private final UserManager userManager;
    private final QandAService service;
    private final RendererManager rendererMgr;
    private final AvatarService avatarService;

    public QandAIssuePanel(final WebResourceManager webResourceManager,
                           final ApplicationProperties properties,
                           final UserManager userManager,
                           final PermissionManager permissionManager,
                           final JiraAuthenticationContext authContext,
                           final QandAService service,
                           final RendererManager rendererMgr,
                           final AvatarService avatarService) {
        this.webResourceManager = webResourceManager;
        this.properties = properties;
        this.userManager = userManager;
        this.permissionManager = permissionManager;
        this.authContext = authContext;
        this.service = service;
        this.rendererMgr = rendererMgr;
        this.avatarService = avatarService;
    }

    private String getText(String key) {
        return descriptor.getI18nBean().getText(key);
    }

    @Override
    public List<IssueAction> getActions(final Issue issue, ApplicationUser user) {
        webResourceManager.requireResource("ro.agrade.jira.qanda-pro:qanda-resources");
        webResourceManager.requireResource("com.atlassian.auiplugin:aui-experimental-lozenge");

        ApplicationUser currentUser = authContext.getUser();
        boolean canOverrideActions = PermissionChecker.isUserLeadOrAdmin(permissionManager, issue, currentUser);
        boolean canAddToIssue = PermissionChecker.isIssueEditable(permissionManager, issue, user);
        UIFormatter formatter = new UIFormatter(userManager, authContext, avatarService, properties, rendererMgr, issue);
        String baseURL = JIRAUtils.getRelativeJIRAPath(properties);

        List<IssueAction> actions = new ArrayList<IssueAction>();
        List<Question> questions = service.loadQuestionsForIssue(issue.getKey());

        QandAStatistics stats = calculateStats(questions);

        // add first action with null Q to add title and add Q button
        actions.add(new QandAIssueAction(descriptor, issue, currentUser, null,
                                         canOverrideActions, canAddToIssue,
                                         baseURL, stats, formatter));
        
        if(questions == null || questions.size() == 0){
        	return actions;
        }
        // for each Q add a new action
        for(Question q : questions) {
            actions.add(new QandAIssueAction(descriptor, issue, currentUser,  q,
                                             canOverrideActions, canAddToIssue, baseURL, stats, formatter));
        }
        return actions;
    }

    private QandAStatistics calculateStats(List<Question> questions) {
        int unresolved = 0;
        for(Question q : questions) {
            if(!q.isClosed()) {
                unresolved ++;
            }
        }
        return new QandAStatistics(questions.size(), unresolved);
    }

    @Override
    public boolean showPanel(Issue issue, ApplicationUser user) {
        return PermissionChecker.canViewIssue(permissionManager, issue, user);
    }

}
