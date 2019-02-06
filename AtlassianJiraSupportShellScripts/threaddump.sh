########################################################################################################################
#AIM		: To generate the thread dump & CPU usage
#INPUT		: NIL
#OUTPUT		: It generates a file in same folder - jira_cpu_usage.1488811709.txt & thread dumps in catalina.out file
#How to Run	: ./threaddump.sh

#######################################################################################################################

#The line below gets the PID from running jira instance and its sted in a JIRA_PID variable


JIRA_PID=`ps aux | grep -i jira | grep -i java | awk  -F '[ ]*' '{print $2}'`;

# The below command generates the cpu usage.txt file in script running folder and thread dump in catilina.out file.
for i in $(seq 6); do top -b -H -p $JIRA_PID -n 1 > jira_cpu_usage.`date +%s`.txt; kill -3 $JIRA_PID; sleep 10; done

#######################################################################################################################
