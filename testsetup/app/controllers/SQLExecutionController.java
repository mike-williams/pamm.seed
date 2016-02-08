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

    @Transactional public Result execute(String testScriptName)
    {
        logger.info("executing script SQLExecutionController id: " + testScriptName);

        String returnValue = null;

        try
        {
            returnValue = dao.executeQuery(testScriptName);
        }
        catch (Exception e)
        {
            logger.info("Exception executing query - " + e.getMessage());
        }

        logger.info("executed script SQLExecutionController return value: " + returnValue);

        return ok(returnValue);
    }
}
