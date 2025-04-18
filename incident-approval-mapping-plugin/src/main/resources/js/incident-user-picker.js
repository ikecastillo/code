(function ($) {

    function createIncidentSingleUserPickers(ctx) {

        var restPath = "/rest/api/1.0/users/picker";

        $(".incidentuserpickerfield", ctx).each(function () {
            var $this = $(this);
            if ($this.data("aui-ss")) return;
            var data = {showAvatar: false},
                inputText = $this.data('inputValue');

            new AJS.SingleSelect({
                element: $this,
                submitInputVal: true,
                showDropdownButton: !!$this.data('show-dropdown-button'),
                errorMessage: AJS.I18n.getText("user.picker.invalid.user", "'{0}'"),
                ajaxOptions: {
                    url: AJS.contextPath() + restPath,
                    query: true, // keep going back to the sever for each keystroke
                    data: data,
                    formatResponse: JIRA.UserPickerUtil.formatResponse
                },
                inputText: inputText
            });
        });
        
     }

    JIRA.bind(JIRA.Events.NEW_CONTENT_ADDED, function (e, context, reason) {
        if (reason !== JIRA.CONTENT_ADDED_REASON.panelRefreshed) {
            createIncidentSingleUserPickers(context);			
        }
    });

})(AJS.$);



