package tutorial;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.account.AccountList;
import com.stormpath.sdk.application.Application;
import com.stormpath.sdk.client.Client;
import com.stormpath.sdk.client.Clients;
import com.stormpath.sdk.directory.CustomData;
import com.stormpath.sdk.lang.Strings;
import com.stormpath.sdk.servlet.account.AccountResolver;







/**
 * Servlet implementation class DashboardController
 */
@WebServlet("/DashboardController")
public class DashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW_TEMPLATE_PATH = "/WEB-INF/jsp/dashboard.jsp";
	
    Logger logger = (Logger) LoggerFactory.getLogger(getClass().getName()+".class");
    
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String birthday = "";
        String color = "";
 
        Account account = AccountResolver.INSTANCE.getAccount(req);
        if (account != null) {
            CustomData data = account.getCustomData();
            birthday = (String)data.get("birthday");
            color = (String)data.get("color");
        }
 
        req.setAttribute("birthday", birthday);
        req.setAttribute("color", color);
        req.getRequestDispatcher(VIEW_TEMPLATE_PATH).forward(req, resp);
        
        Client client = Clients.builder().build();
        Application application =
        	      client.getResource(System.getenv("STORMPATH_APPLICATION_HREF"), Application.class);
        logger.debug("STORMPATH_APPLICATION_HREF: "+System.getenv("STORMPATH_APPLICATION_HREF"));
        
        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("username", "tk421");
        AccountList accounts = application.getAccounts();
        logger.debug("########################################");
        for (Account acct : accounts) {
           logger.debug("Found Account: " + acct.getHref() + ", " + acct.getEmail());
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String birthday = req.getParameter("birthday");
        String color = req.getParameter("color");
 
        //get the currently-logged-in account:
        Account account = AccountResolver.INSTANCE.getAccount(req);
        if (account != null) {
 
            CustomData data = account.getCustomData();
 
            if (Strings.hasText(birthday)) {
                data.put("birthday", birthday);
            } else {
                data.remove("birthday");
            }
            
            if (Strings.hasText(color)) {
                data.put("color", color);
            } else {
                data.remove("color");
            }
 
            data.save();
        }
 
        req.setAttribute("birthday", birthday);
        req.setAttribute("color", color);
        req.getRequestDispatcher(VIEW_TEMPLATE_PATH).forward(req, resp);
	}

}
