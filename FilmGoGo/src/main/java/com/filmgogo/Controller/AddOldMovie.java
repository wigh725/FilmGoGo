package com.filmgogo.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.filmgogo.DAO.AddOldMovieDAO;
import com.filmgogo.DAO.CustomerDAO;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/addoldmovie", method=RequestMethod.POST)
public class AddOldMovie {
	@Autowired
	private AddOldMovieDAO addOldMovieDAO;
	
	@RequestMapping
    void dealRequest(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
		request.setCharacterEncoding("UTF-8");
    	StringBuffer requestData = new StringBuffer();
		String line = null;
		try
		{
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
			{
				requestData.append(line);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		JSONObject requestInfo = JSONObject.fromObject(requestData.toString());
		JSONObject res = new JSONObject();
		res.put("success", true);
		addOldMovieDAO.addOldMovie(requestInfo.getString("name"), requestInfo.getString("description"), 
				requestInfo.getString("image"),requestInfo.getString("time"), requestInfo.getString("price")
				,requestInfo.getString("type"), requestInfo.getString("star"), requestInfo.getString("score"));
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(res.toString());
    }
    
}
