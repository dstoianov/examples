package tipsandtricks.redmine.api;

import com.taskadapter.redmineapi.Include;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.Project;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Funker on 18.12.2014.
 */
public class Redmine {

    private static String uri = "https://www.hostedredmine.com";
    private static String apiAccessKey = "a3221bfcef5750219bd0a2df69519416dba17fc9";
    private static String projectKey = "taskconnector-test";
    private static Integer queryId = null; // any


    @Test
    public void testName1() throws Exception {
        RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
        List<Issue> issues = mgr.getIssueManager().getIssues(projectKey, queryId);

        for (Issue issue : issues) {
            System.out.println(issue.toString() + "  " + issue.getStatusName() + "  " + issue.getTracker().getName());
        }

//        to long because to mane projects
        //List<Project> projects = mgr.getProjectManager().getProjects();

        Issue issueById = mgr.getIssueManager().getIssueById(2272, Include.attachments);
        System.out.println(issueById.getStatusName());
    }

    // http://demo.redmine.org/projects/fnk/issues
    @Test
    public void testName2() throws Exception {
        RedmineManager mgr = RedmineManagerFactory.createWithUserAuth("http://demo.redmine.org/", "funker", "222222");

        List<Issue> fnk = mgr.getIssueManager().getIssues("fnk", null);

        for (Issue issue : fnk) {
            System.out.println(issue.toString() + "  " + issue.getStatusName() + "  " + issue.getTracker().getName());
        }

        mgr.getIssueManager().getIssues("fnk", null);


        List<Project> projects = mgr.getProjectManager().getProjects();
        for (Project project : projects) {
            if (project.getName().contains("funker")) {
                System.out.println(project.toString());
            }
        }
        //mgr.getIssueManager().getIssues("Funker test Project", queryId);
    }


}
