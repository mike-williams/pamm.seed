package repository;

import play.Logger;
import util.ApplicationRootHelper;
import util.FileReaderUtility;

import javax.inject.Inject;
import java.io.File;

public class Dao {
    private static final Logger.ALogger LOG = Logger.of(Dao.class);

    private final EntityManagerProvider emp;
    private final FileReaderUtility sqlReader;
    private final ApplicationRootHelper appRootHelper;

    @Inject
    public Dao(EntityManagerProvider emp,
               FileReaderUtility sqlReader,
               ApplicationRootHelper appRootHelper) {
        this.emp = emp;
        this.sqlReader = sqlReader;
        this.appRootHelper = appRootHelper;
    }

    public final String executeQuery(final String script) {
        final String sqlFilePath = appRootHelper.getApplicationRootPath() + File.separatorChar + script;

        LOG.info("SQL File Path: " + sqlFilePath);

        try {
            final String sqlString;
            sqlString = sqlReader.readFile(sqlFilePath);
            emp.getEntityManager().createNativeQuery(sqlString).executeUpdate();
            return "Query executed succesfully";
        } catch (Exception e) {
            return "Error reading SQL file " + sqlFilePath + " -> exception: " + e.getMessage();
        }
    }
}
