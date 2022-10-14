import com.google.gson.Gson;
import io.swagger.client.JSON;
import io.swagger.client.model.LiftRide;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SkierServlet", value = "/SkierServlet")
public class SkierServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/plain");
//        String urlPath = request.getPathInfo();
//
//        // check we have a URL!
//        if (urlPath == null || urlPath.isEmpty()) {
//            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//            response.getWriter().write("missing paramterers");
//            return;
//        }
//
//        String[] urlParts = urlPath.split("/");
//        // and now validate url path and return the response status code
//        // (and maybe also some value if input is valid)
//
//        if (!isUrlValid(urlParts)) {
//            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//        } else {
//            response.setStatus(HttpServletResponse.SC_OK);
//            // do any sophisticated processing with urlParts which contains all the url params
//            // TODO: process url params in `urlParts`
//            response.getWriter().write("It works!");
//        }
        response.getWriter().write("It works!");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        Gson gson = new Gson();
//        String urlPath = request.getPathInfo();
//
//        // check we have a URL!
//        if (urlPath == null || urlPath.isEmpty()) {
//            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//            response.getWriter().write("missing parameters");
//            return;
//        }
//
//        String[] urlParts = urlPath.split("/");
//        // and now validate url path and return the response status code
//        // (and maybe also some value if input is valid)
//
//        int resStatus = getResStatus(urlParts);
//        response.setStatus(resStatus);
//        if (resStatus == 201) {
//            // do any sophisticated processing with urlParts which contains all the url params
//            // TODO: process url params in `urlParts`
//            response.getWriter().write("It works!");
//            JSON json = new JSON();
//            LiftRide liftRide = json.deserialize(request.getReader().toString(), LiftRide.class);
//        }
//        response.getWriter().write("It works!");

//        response.setStatus(HttpServletResponse.SC_OK);
        Status status = new Status(true, "It works!");
        response.getOutputStream().print(gson.toJson(status));
    }

    private boolean isUrlValid(String[] urlPath) {
        // TODO: validate the request url path according to the API spec
        int dayId = Integer.parseInt(urlPath[4]);
        if (!isInteger(urlPath[0]) || !isInteger(urlPath[6]) || dayId < 1 || dayId > 366) {
            return false;
        }

        if (urlPath.length != 7  || !urlPath[1].equals("seasons")
                || !urlPath[3].equals("days") || !urlPath[5].equals("skiers")) {
            return false;
        }

        return true;
    }

    private int getResStatus(String[] urlPath) {
        // TODO: validate the request url path according to the API spec
        int dayId = Integer.parseInt(urlPath[5]);
        if (!isInteger(urlPath[1]) || !isInteger(urlPath[7]) || dayId < 1 || dayId > 366) {
            return HttpServletResponse.SC_BAD_REQUEST;
        }

        if (urlPath.length != 8 || !urlPath[0].equals("skiers") || !urlPath[2].equals("seasons")
                || !urlPath[4].equals("days") || !urlPath[6].equals("skiers")) {
            return HttpServletResponse.SC_NOT_FOUND;
        }

        return HttpServletResponse.SC_CREATED;
    }

    private boolean isInteger(String val) {
        try {
            Integer.parseInt(val);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
