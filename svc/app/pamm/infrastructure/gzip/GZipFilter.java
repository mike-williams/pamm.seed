package pamm.infrastructure.gzip;

import play.api.mvc.EssentialFilter;
import play.http.HttpFilters;
import play.filters.gzip.GzipFilter;
import javax.inject.Inject;

public class GZipFilter implements HttpFilters{

    private final GzipFilter gzip;

    @Inject
    public GZipFilter(GzipFilter gzip) {
        this.gzip = gzip;
    }

    @Override
    public EssentialFilter[] filters() {
        return new EssentialFilter[] {
                gzip
        };
    }

}

