<%--
  ~ Copyright 2015 Red Hat, Inc. and/or its affiliates.
  ~
  ~ Licensed under the Apache License, Version 2.0 (the "License");
  ~ you may not use this file except in compliance with the License.
  ~ You may obtain a copy of the License at
  ~
  ~       http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing, software
  ~ distributed under the License is distributed on an "AS IS" BASIS,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  ~ See the License for the specific language governing permissions and
  ~ limitations under the License.
  --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page import="org.jboss.errai.security.server.FormAuthenticationScheme" %>
<%
    String contextPath = getServletContext().getContextPath();
%>
<html xmlns="http://www.w3.org/1999/xhtml" class="login-pf">
<head>
    <title>LiveSpark: Demo</title>

    <link href="<%=contextPath%>/bootstrap-3/css/bootstrap.min.css" rel="stylesheet">

    <link href='http://fonts.googleapis.com/css?family=Gudea:400,700' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Inconsolata' rel='stylesheet' type='text/css'>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <% if ( request.getParameter( FormAuthenticationScheme.LOGIN_ERROR_QUERY_PARAM ) != null ) { %>
            <div class="alert alert-danger">
                Login failed. Please try again.
            </div>
            <% } %>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form class="form-horizontal" role="form" action="uf_security_check" method="post">
                        <div class="form-group">
                            <label for="uf_username" class="col-sm-2 col-md-2 control-label">Username</label>
                            <div class="col-sm-10 col-md-10">
                                <input type="text" class="form-control" id="uf_username" name="uf_username" placeholder="admin" tabindex="1" autofocus>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="uf_password" class="col-sm-2 col-md-2 control-label">Password</label>
                            <div class="col-sm-10 col-md-10">
                                <input type="password" class="form-control" id="uf_password" name="uf_password" placeholder="admin" tabindex="2">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="btn-group pull-right">
                                    <button type="submit" class="btn btn-primary" tabindex="3">Log In</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>

</body>
</html>
