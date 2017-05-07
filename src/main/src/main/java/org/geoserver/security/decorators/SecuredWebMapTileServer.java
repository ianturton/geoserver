/* (c) 2014 Open Source Geospatial Foundation - all rights reserved
 * (c) 2001 - 2013 OpenPlans
 * This code is licensed under the GPL 2.0 license, available at the root
 * application directory.
 */
package org.geoserver.security.decorators;

import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;

import org.geotools.data.ResourceInfo;
import org.geotools.data.ServiceInfo;
import org.geotools.data.ows.GetCapabilitiesRequest;
import org.geotools.data.ows.GetCapabilitiesResponse;
import org.geotools.data.ows.Layer;
import org.geotools.data.ows.WMSCapabilities;
import org.geotools.data.wms.WebMapServer;
import org.geotools.data.wms.request.DescribeLayerRequest;
import org.geotools.data.wms.request.GetFeatureInfoRequest;
import org.geotools.data.wms.request.GetLegendGraphicRequest;
import org.geotools.data.wms.request.GetMapRequest;
import org.geotools.data.wms.request.GetStylesRequest;
import org.geotools.data.wms.request.PutStylesRequest;
import org.geotools.data.wms.response.DescribeLayerResponse;
import org.geotools.data.wms.response.GetFeatureInfoResponse;
import org.geotools.data.wms.response.GetLegendGraphicResponse;
import org.geotools.data.wms.response.GetMapResponse;
import org.geotools.data.wms.response.GetStylesResponse;
import org.geotools.data.wms.response.PutStylesResponse;
import org.geotools.data.wmts.WMTSCapabilities;
import org.geotools.data.wmts.WebMapTileServer;
import org.geotools.data.wmts.request.GetTileRequest;
import org.geotools.geometry.GeneralEnvelope;
import org.geotools.ows.ServiceException;
import org.geotools.tile.Tile;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

/**
 * Applies security around the web map server
 * @author Andrea Aime - GeoSolutions
 *
 */
public class SecuredWebMapTileServer extends WebMapTileServer {

    WebMapTileServer delegate;

    public SecuredWebMapTileServer(WebMapTileServer delegate) throws IOException, ServiceException {
        super(delegate.getCapabilities());
        this.delegate = delegate;
    }
    
    public GetFeatureInfoRequest createGetFeatureInfoRequest(GetTileRequest getTileRequest) {
        return new SecuredGetFeatureInfoRequest(delegate.createGetFeatureInfoRequest(getTileRequest), getTileRequest);
    }
    
    public GetTileRequest createGetTileRequest() {
        return new SecuredGetTileRequest(delegate.createGetTileRequest());
    }
    
    // -------------------------------------------------------------------------------------------
    //
    // Purely delegated methods
    //
    // -------------------------------------------------------------------------------------------

    

    public GetLegendGraphicResponse issueRequest(GetLegendGraphicRequest request)
            throws IOException, ServiceException {

        return null;//delegate.issueRequest(request);
    }

    public GetCapabilitiesResponse issueRequest(GetCapabilitiesRequest request) throws IOException,
            ServiceException {
        return delegate.issueRequest(request);
    }

    public GetFeatureInfoResponse issueRequest(GetFeatureInfoRequest request) {
        return delegate.issueRequest(request);
    }

    public Set<Tile> issueRequest(GetTileRequest request) throws ServiceException {
        return delegate.issueRequest(request);
    }

    public boolean equals(Object obj) {
        return delegate.equals(obj);
    }

    public WMTSCapabilities getCapabilities() {
        return delegate.getCapabilities();
    }

    public GeneralEnvelope getEnvelope(Layer layer, CoordinateReferenceSystem crs) {
        return delegate.getEnvelope(layer, crs);
    }

    public ServiceInfo getInfo() {
        return delegate.getInfo();
    }

    public ResourceInfo getInfo(Layer resource) {
        return delegate.getInfo(resource);
    }

    public int hashCode() {
        return delegate.hashCode();
    }

    public void setLoggingLevel(Level newLevel) {
        delegate.setLoggingLevel(newLevel);
    }

    public String toString() {
        return "SecuredWebMapTileServer " + delegate.toString();
    }

}
