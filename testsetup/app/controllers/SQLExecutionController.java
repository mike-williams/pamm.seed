package controllers;

import play.Logger;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import repository.Dao;

import javax.inject.Inject;

public class SQLExecutionController extends Controller
{
    private static final Logger.ALogger logger = Logger.of(SQLExecutionController.class);

    private Dao dao;

    @Inject public SQLExecutionController(Dao dao)
    {
        this.dao = dao;
    }

    @Transactional public Result update(String testScriptName)
    {
        try
        {
            logger.info("executing update script: " + testScriptName);
            return ok(dao.executeUpdate(testScriptName));
        }
        catch (Exception e)
        {
            logger.info("Exception executing query - " + e.getMessage());
            return internalServerError(e.getMessage());
        }


    }

    @Transactional public Result query(String testScriptName)
    {
        try
        {
            logger.info("executing query script: " + testScriptName);
            return ok(dao.executeQuery(testScriptName));
        }
        catch (Exception e)
        {
            logger.info("Exception executing query - " + e.getMessage());
            return internalServerError(e.getMessage());
        }

    }

}
