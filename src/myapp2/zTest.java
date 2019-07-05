package myapp2;

import java.io.File;

public class zTest {

	public static void main(String[] args) {
		File f = new File("E:\\方向论文\\隐性冲突\\gchq\\Gaffer\\fb0d4dfbe54b0d1abb73800f1dcfb89e49c26e79\\0FreqMapPredicatorTest.java/right.java");
		String s = "package com.adobe.acs.commons.forms.helpers.impl;\r\n" + 
				"\r\n" + 
				"import com.adobe.acs.commons.forms.Form;\r\n" + 
				"import com.adobe.acs.commons.forms.helpers.FormHelper;\r\n" + 
				"import com.adobe.acs.commons.forms.helpers.PostFormHelper;\r\n" + 
				"import com.adobe.acs.commons.util.PathInfoUtil;\r\n" + 
				"import com.adobe.granite.xss.XSSAPI;\r\n" + 
				"import com.day.cq.wcm.api.Page;\r\n" + 
				"import org.apache.commons.lang.ArrayUtils;\r\n" + 
				"import org.apache.commons.lang.StringUtils;\r\n" + 
				"import org.apache.felix.scr.annotations.*;\r\n" + 
				"import org.apache.sling.api.SlingHttpServletRequest;\r\n" + 
				"import org.apache.sling.api.SlingHttpServletResponse;\r\n" + 
				"import org.apache.sling.api.request.RequestParameter;\r\n" + 
				"import org.apache.sling.api.request.RequestParameterMap;\r\n" + 
				"import org.apache.sling.api.resource.LoginException;\r\n" + 
				"import org.apache.sling.api.resource.Resource;\r\n" + 
				"import org.apache.sling.api.resource.ResourceResolver;\r\n" + 
				"import org.apache.sling.api.resource.ResourceResolverFactory;\r\n" + 
				"import org.apache.sling.commons.json.JSONException;\r\n" + 
				"import org.apache.sling.commons.osgi.PropertiesUtil;\r\n" + 
				"import org.osgi.framework.Constants;\r\n" + 
				"import org.slf4j.Logger;\r\n" + 
				"import org.slf4j.LoggerFactory;\r\n" + 
				"\r\n" + 
				"import javax.servlet.ServletException;\r\n" + 
				"import java.io.IOException;\r\n" + 
				"import java.io.UnsupportedEncodingException;\r\n" + 
				"import java.util.HashMap;\r\n" + 
				"import java.util.Map;\r\n" + 
				"\r\n" + 
				"@Component(label = \"ACS AEM Commons - Abstract POST Form Helper\", description = \"Abstract Form Helper. Do not use directly; instead use the PostRedirectGetFormHelper or ForwardAsGetFormHelper.\", enabled = true, metatype = false, immediate = false)\r\n" + 
				"@Properties({ @Property(label = \"Vendor\", name = Constants.SERVICE_VENDOR, value = \"ACS\", propertyPrivate = true) })\r\n" + 
				"@Service(value = PostFormHelper.class)\r\n" + 
				"public class PostFormHelperImpl implements PostFormHelper {\r\n" + 
				"    private static final Logger log = LoggerFactory.getLogger(PostFormHelperImpl.class);\r\n" + 
				"    protected static final String[] FORM_INPUTS = { FORM_NAME_INPUT, FORM_RESOURCE_INPUT };\r\n" + 
				"\r\n" + 
				"    @Reference\r\n" + 
				"    protected ResourceResolverFactory resourceResolverFactory;\r\n" + 
				"\r\n" + 
				"    @Reference\r\n" + 
				"    protected XSSAPI xssApi;\r\n" + 
				"\r\n" + 
				"    /**\r\n" + 
				"     * OSGi Properties *\r\n" + 
				"     */\r\n" + 
				"    private static final String DEFAULT_SUFFIX = \"/submit/form\";\r\n" + 
				"    private String suffix = DEFAULT_SUFFIX;\r\n" + 
				"    @Property(label = \"Suffix\", description = \"Forward-as-GET Request Suffix used to identify Forward-as-GET POST Request\", value = DEFAULT_SUFFIX)\r\n" + 
				"    private static final String PROP_SUFFIX = \"prop.form-suffix\";\r\n" + 
				"\r\n" + 
				"    @Override\r\n" + 
				"    public Form getForm(String formName, SlingHttpServletRequest request) {\r\n" + 
				"        throw new UnsupportedOperationException(\"Do not call AbstractFormHelper.getForm(..) direct. This is an abstract service.\");\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    @Override\r\n" + 
				"	public String getFormInputsHTML(final Form form, final String... keys) {\r\n" + 
				"        // The form objects data and errors should be xssProtected before being passed into this method\r\n" + 
				"		StringBuffer html = new StringBuffer();\r\n" + 
				"\r\n" + 
				"        html.append(\"<input type=\\\"hidden\\\" name=\\\"\").append(FORM_NAME_INPUT).append(\"\\\" value=\\\"\")\r\n" + 
				"                .append(xssApi.encodeForHTMLAttr(form.getName())).append(\"\\\"/>\\r\n\");\r\n" + 
				"\r\n" + 
				"        final String resourcePath = form.getResourcePath();\r\n" + 
				"        html.append(\"<input type=\\\"hidden\\\" name=\\\"\").append(FORM_RESOURCE_INPUT).append(\"\\\" value=\\\"\")\r\n" + 
				"                .append(xssApi.encodeForHTMLAttr(resourcePath)).append(\"\\\"/>\\r\n\");\r\n" + 
				"\r\n" + 
				"		for (final String key : keys) {\r\n" + 
				"			if (form.has(key)) {\r\n" + 
				"				html.append(\"<input type=\\\"hidden\\\" name=\\\"\").append(key).append(\"\\\" value=\\\"\")\r\n" + 
				"						.append(form.get(key)).append(\"\\\"/>\\r\n\");\r\n" + 
				"			}\r\n" + 
				"		}\r\n" + 
				"\r\n" + 
				"		return html.toString();\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"    @Override\r\n" + 
				"    public String getAction(final String path) {\r\n" + 
				"        return getAction(path, null);\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    @Override\r\n" + 
				"    public String getAction(final String path, final String formSelector) {\r\n" + 
				"        String actionPath = path;\r\n" + 
				"\r\n" + 
				"        ResourceResolver adminResourceResolver = null;\r\n" + 
				"        try {\r\n" + 
				"            adminResourceResolver = resourceResolverFactory.getAdministrativeResourceResolver(null);\r\n" + 
				"            actionPath = adminResourceResolver.map(path);\r\n" + 
				"        } catch (LoginException e) {\r\n" + 
				"            log.error(\"Could not attain an admin ResourceResolver to map the Form's Action URI\");\r\n" + 
				"            // Use the unmapped ActionPath\r\n" + 
				"        } finally {\r\n" + 
				"            if(adminResourceResolver != null && adminResourceResolver.isLive()) {\r\n" + 
				"                adminResourceResolver.close();\r\n" + 
				"            }\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        actionPath += FormHelper.EXTENSION + this.getSuffix();\r\n" + 
				"        if(StringUtils.isNotBlank(formSelector)) {\r\n" + 
				"            actionPath += \"/\" + formSelector;\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        return actionPath;\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    @Override\r\n" + 
				"    public void renderForm(Form form, String path, SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException, ServletException, JSONException {\r\n" + 
				"        throw new UnsupportedOperationException(\"Use a specific Forms implementation helper.\");\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    @Override\r\n" + 
				"    public void renderForm(Form form, Page page, SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException, ServletException, JSONException {\r\n" + 
				"        throw new UnsupportedOperationException(\"Use a specific Forms implementation helper.\");\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    @Override\r\n" + 
				"    public void renderForm(Form form, Resource resource, SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException, ServletException, JSONException {\r\n" + 
				"        throw new UnsupportedOperationException(\"Use a specific Forms implementation helper.\");\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    @Override\r\n" + 
				"    public void renderOtherForm(Form form, String path, String selectors, SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException, ServletException, JSONException {\r\n" + 
				"        throw new UnsupportedOperationException(\"Use a specific Forms implementation helper.\");\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    @Override\r\n" + 
				"    public void renderOtherForm(Form form, Page page, String selectors, SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException, ServletException, JSONException {\r\n" + 
				"        throw new UnsupportedOperationException(\"Use a specific Forms implementation helper.\");\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    @Override\r\n" + 
				"    public void renderOtherForm(Form form, Resource resource, String selectors, SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException, ServletException, JSONException {\r\n" + 
				"        throw new UnsupportedOperationException(\"Use a specific Forms implementation helper.\");\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    @Override\r\n" + 
				"    public String getAction(final Resource resource) {\r\n" + 
				"        return getAction(resource.getPath());\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    @Override\r\n" + 
				"    public String getAction(final Resource resource, final String formSelector) {\r\n" + 
				"        return getAction(resource.getPath(), formSelector);\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    @Override\r\n" + 
				"    public String getAction(final Page page) {\r\n" + 
				"        return getAction(page.getPath());\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    @Override\r\n" + 
				"    public String getAction(final Page page, final String formSelector) {\r\n" + 
				"        return getAction(page.getPath(), formSelector);\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    @Override\r\n" + 
				"    public String getSuffix() {\r\n" + 
				"        return this.suffix;\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    /**\r\n" + 
				"     * Determines of this FormHelper should handle the POST request\r\n" + 
				"     *\r\n" + 
				"     * @param formName\r\n" + 
				"     * @param request\r\n" + 
				"     * @return\r\n" + 
				"     */\r\n" + 
				"    protected boolean doHandlePost(final String formName, final SlingHttpServletRequest request) {\r\n" + 
				"        if(StringUtils.equalsIgnoreCase(\"POST\", request.getMethod())) {\r\n" + 
				"            // Form should have a hidden input with the name this.getLookupKey(..) and value formName\r\n" + 
				"            return StringUtils.equals(formName, request.getParameter(this.getPostLookupKey(formName)));\r\n" + 
				"        } else {\r\n" + 
				"            return false;\r\n" + 
				"        }\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    /**\r\n" + 
				"     * Gets the Form from POST requests\r\n" + 
				"     *\r\n" + 
				"     * @param formName\r\n" + 
				"     * @param request\r\n" + 
				"     * @return\r\n" + 
				"     */\r\n" + 
				"    protected Form getPostForm(final String formName,\r\n" + 
				"                            final SlingHttpServletRequest request) {\r\n" + 
				"        final Map<String, String> map = new HashMap<String, String>();\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"        final RequestParameterMap requestMap = request.getRequestParameterMap();\r\n" + 
				"\r\n" + 
				"        for (final String key : requestMap.keySet()) {\r\n" + 
				"            // POST LookupKey formName param does not matter\r\n" + 
				"            if(StringUtils.equals(key, this.getPostLookupKey(null))) { continue; }\r\n" + 
				"\r\n" + 
				"            final RequestParameter[] values = requestMap.getValues(key);\r\n" + 
				"\r\n" + 
				"            if (values == null || values.length == 0) {\r\n" + 
				"                log.debug(\"Value did not exist for key: {}\", key);\r\n" + 
				"            } else if (values.length == 1) {\r\n" + 
				"                log.debug(\"Adding to form data: {} ~> {}\", key, values[0].toString());\r\n" + 
				"                map.put(key, values[0].getString());\r\n" + 
				"            } else {\r\n" + 
				"                // TODO: Handle multi-value parameter values; Requires support for transporting them and re-writing them back into HTML Form on error\r\n" + 
				"                for(final RequestParameter value : values) {\r\n" + 
				"                    // Use the first non-blank value, or use the last value (which will be blank or not-blank)\r\n" + 
				"                    final String tmp = value.toString();\r\n" + 
				"                    map.put(key, tmp);\r\n" + 
				"\r\n" + 
				"                    if(StringUtils.isNotBlank(tmp)) {\r\n" + 
				"                        break;\r\n" + 
				"                    }\r\n" + 
				"                }\r\n" + 
				"            }\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        return this.clean(new Form(formName, request.getResource().getPath(), map));\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    /**\r\n" + 
				"     * Gets the Key used to look up the form during handling of POST requests\r\n" + 
				"     *\r\n" + 
				"     * @param formName\r\n" + 
				"     * @return\r\n" + 
				"     */\r\n" + 
				"    protected String getPostLookupKey(final String formName) {\r\n" + 
				"        // This may change; keeping as method call to ease future refactoring\r\n" + 
				"        return FORM_NAME_INPUT;\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    /**\r\n" + 
				"     * Removes unused Map entries from the provided map\r\n" + 
				"     *\r\n" + 
				"     * @param form\r\n" + 
				"     * @return\r\n" + 
				"     */\r\n" + 
				"    protected Form clean(final Form form) {\r\n" + 
				"        final Map<String, String> map = form.getData();\r\n" + 
				"        final Map<String, String> cleanedMap = new HashMap<String, String>();\r\n" + 
				"\r\n" + 
				"        for(final Map.Entry<String, String> entry : map.entrySet()) {\r\n" + 
				"            if(!ArrayUtils.contains(FORM_INPUTS, entry.getKey()) && StringUtils.isNotBlank(entry.getValue())) {\r\n" + 
				"                cleanedMap.put(entry.getKey(), entry.getValue());\r\n" + 
				"            }\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        return new Form(form.getName(), form.getResourcePath(), cleanedMap, form.getErrors());\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    /**\r\n" + 
				"     * Protect a Form in is entirety (data and errors)\r\n" + 
				"     *\r\n" + 
				"     * @param form\r\n" + 
				"     * @return\r\n" + 
				"     */\r\n" + 
				"    protected Form getProtectedForm(final Form form) {\r\n" + 
				"        return new Form(form.getName(),\r\n" + 
				"                form.getResourcePath(),\r\n" + 
				"                this.getProtectedData(form.getData()),\r\n" + 
				"                this.getProtectedErrors(form.getErrors()));\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    /**\r\n" + 
				"     * Protect a Map representing Form Data\r\n" + 
				"     *\r\n" + 
				"     * @param data\r\n" + 
				"     * @return\r\n" + 
				"     */\r\n" + 
				"    protected Map<String, String> getProtectedData(final Map<String, String> data) {\r\n" + 
				"        final Map<String, String> protectedData = new HashMap<String, String>();\r\n" + 
				"\r\n" + 
				"        // Protect data for HTML Attributes\r\n" + 
				"        for (final Map.Entry<String, String> entry : data.entrySet()) {\r\n" + 
				"            protectedData.put(entry.getKey(), xssApi.encodeForHTMLAttr(entry.getValue()));\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        return protectedData;\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    /**\r\n" + 
				"     * Protect a Map representing Form Errors\r\n" + 
				"     *\r\n" + 
				"     * @param errors\r\n" + 
				"     * @return\r\n" + 
				"     */\r\n" + 
				"    protected Map<String, String> getProtectedErrors(final Map<String, String> errors) {\r\n" + 
				"        final Map<String, String> protectedErrors = new HashMap<String, String>();\r\n" + 
				"\r\n" + 
				"        // Protect data for HTML\r\n" + 
				"        for (final Map.Entry<String, String> entry : errors.entrySet()) {\r\n" + 
				"            protectedErrors.put(entry.getKey(), xssApi.encodeForHTML(entry.getValue()));\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        return protectedErrors;\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"    public boolean hasValidSuffix(final SlingHttpServletRequest slingRequest) {\r\n" + 
				"        final String requestSuffix = slingRequest.getRequestPathInfo().getSuffix();\r\n" + 
				"        if(StringUtils.equals(requestSuffix, this.getSuffix()) ||\r\n" + 
				"                StringUtils.startsWith(requestSuffix, this.getSuffix() + \"/\")) {\r\n" + 
				"            return true;\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        return false;\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    /**\r\n" + 
				"     * Gets the Form Selector for the form POST request\r\n" + 
				"     *\r\n" + 
				"     * @param slingRequest\r\n" + 
				"     * @return\r\n" + 
				"     */\r\n" + 
				"    public String getFormSelector(final SlingHttpServletRequest slingRequest) {\r\n" + 
				"        final String requestSuffix = slingRequest.getRequestPathInfo().getSuffix();\r\n" + 
				"        if(StringUtils.equals(requestSuffix, this.getSuffix()) ||\r\n" + 
				"                !StringUtils.startsWith(requestSuffix, this.getSuffix() + \"/\")) {\r\n" + 
				"            return null;\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        final int segments = StringUtils.split(this.getSuffix(), '/').length;\r\n" + 
				"        if(segments < 1) {\r\n" + 
				"            return null;\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        final String formSelector = PathInfoUtil.getSuffixSegment(slingRequest, segments);\r\n" + 
				"        return StringUtils.stripToNull(formSelector);\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    /**\r\n" + 
				"     * Encodes URL data\r\n" + 
				"     *\r\n" + 
				"     * @param unencoded\r\n" + 
				"     * @return\r\n" + 
				"     */\r\n" + 
				"    protected String encode(String unencoded) {\r\n" + 
				"        if(StringUtils.isBlank(unencoded)) {\r\n" + 
				"            return \"\";\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        try {\r\n" + 
				"            return java.net.URLEncoder.encode(unencoded, \"UTF-8\");\r\n" + 
				"        } catch (UnsupportedEncodingException e) {\r\n" + 
				"            return unencoded;\r\n" + 
				"        }\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    /**\r\n" + 
				"     * Decodes URL data\r\n" + 
				"     *\r\n" + 
				"     * @param encoded\r\n" + 
				"     * @return\r\n" + 
				"     */\r\n" + 
				"    protected String decode(String encoded) {\r\n" + 
				"        if(StringUtils.isBlank(encoded)) {\r\n" + 
				"            return \"\";\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        try {\r\n" + 
				"            return java.net.URLDecoder.decode((encoded), \"UTF-8\");\r\n" + 
				"        } catch (UnsupportedEncodingException e) {\r\n" + 
				"            return encoded;\r\n" + 
				"        }\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    @Activate\r\n" + 
				"    protected void activate(final Map<String, String> properties) {\r\n" + 
				"        this.suffix = PropertiesUtil.toString(properties.get(PROP_SUFFIX), DEFAULT_SUFFIX);\r\n" + 
				"        if(StringUtils.isBlank(this.suffix)) {\r\n" + 
				"            // No whitespace please\r\n" + 
				"            this.suffix = DEFAULT_SUFFIX;\r\n" + 
				"        }\r\n" + 
				"    }\r\n" + 
				"}";
		System.out.println(s.substring(1842, 1928+1));
	}

}
