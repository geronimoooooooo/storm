 Dashboard
 
        <div class="dashboard">
            <div class="row">
                <div class="col-lg-12">
                    <div class="jumbotron">
                        <h1>Dashboard</h1>
 
                        <br />
                        <br />
 
                        <p>Welcome to your user dashboard!</p>
 
                        <p>This page displays some of your account information and also allows you to change custom
                            data.</p>
 
                        <p>If you click the Logout link in the navbar at the top of this page, you'll be logged out
                            of your account and redirected back to the main page of this site.</p>
                        <br />
                        <br />
 
                        <h2>Your Account Custom Data</h2>
                        <br />
                        <br />
 
                        <p>Your Email: <span class="data">${account.email}</span></p>
 
 
                        <p>Your Birthday: <span class="data">${!empty account.customData['birthday'] ? account.customData['birthday'] : noBirthday}</span></p>
 
 
                        <p>Your Favorite Color: <span class="data">${!empty account.customData['color'] ? account.customData['color'] : noColor}</span></p>
 
                        <br />
                        <br />
 
                        <p>Stormpath allows you to store up to 10MB of custom user data on
                            each user account. Data can be anything (in JSON format). The above
                            example shows two custom fields (<code>birthday</code> and
                            <code>color</code>), but you can add whatever fields you'd like.</p>
 
                        <p>You can also store complicated nested JSON documents!</p>
                        <br />
                        <br />
 
                        <h2>Update Custom Data</h2>
                        <br />
                        <br />
 
                        <p>If you enter values below, we'll send and store these
                            values with your user account on Stormpath.</p>
 
                        <p>Please note, we are not doing any validation in this simple
                            example -- in a real world scenario, you'd want to check user input on the server side!</p>
                        <br />
                        <br />
 
                        <form method="post" class="bs-example form-horizontal" action="${pageContext.request.contextPath}/dashboard">
                            <div class="form-group">
                                <label for="birthday" class="col-lg-2 control-label">Birthday</label>
 
                                <div class="col-lg-4">
 
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="color" class="col-lg-2 control-label">Favorite Color</label>
                                <div class="col-lg-4">
 
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-lg-10 col-lg-offset-2">
                                    <button type="submit" class="btn btn-primary">Update Custom Data</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>