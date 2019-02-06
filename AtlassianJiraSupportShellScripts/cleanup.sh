
##########################################################################################################
#AIM: To clean up the old files
#INPUT: NIL
#OUTPUT: It clears the 30 days old files in provided folders
#How to run: ./cleanup.sh

#Note:There might be path changes in Development, Staging and Prodcution

##########################################################################################################


# The command below delete more than 30 days older access_log files from installed directory
find /opt/atlassian/jira/logs -name "access_log.*" -mtime +30 -exec rm -f {} \;

# The command below delete more than 30 days older catalina files from installed directory
find /opt/atlassian/jira/logs -name "catalina.*" -mtime +30 -exec rm -f {} \;
find /opt/atlassian/jira/logs -name "host-manager.*" -mtime +30 -exec rm -f {} \;
find /opt/atlassian/jira/logs -name "localhost.*" -mtime +30 -exec rm -f {} \;
find /opt/atlassian/jira/logs -name "manager.*" -mtime +30 -exec rm -f {} \;

# The command below delete more than 30 days older backup files from daily back 
find /opt/app/atlassian/backup/Jira -name "*.zip" -mtime +30 -exec rm -f {} \;

# The command below delete the 30 days or more from jira home directory
find /var/atlassian/application-data/jira/log -name "*.log*" -mtime +30 -exec rm -f {} \;


##########################################################################################################
