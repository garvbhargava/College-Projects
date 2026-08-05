package com.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.PostDAO;
import com.User.UserDetails;
import com.util.WebUtils;

/**
 * Deletes a note the signed-in user owns.
 *
 * <p>POST only. This was a GET link taking just a note id, so it could delete
 * any user's note by number and could be triggered from any other site.
 */
@WebServlet("/deleteServlet")
public class deleteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserDetails user = WebUtils.currentUser(request);
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int noteId = WebUtils.intParam(request, "note_id", -1);

        if (noteId >= 1 && new PostDAO().deleteNote(noteId, user.getId())) {
            WebUtils.success(request, "Note deleted.");
        } else {
            WebUtils.error(request, "That note could not be found.");
        }

        response.sendRedirect("showNotes.jsp");
    }
}
