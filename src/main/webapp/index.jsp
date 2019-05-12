<html>
<head>
  <title>Login page</title>
</head>
<body>

<form method="post"  action="hello">
  <h2>Sign In:</h2>
  <input type="text" id="login-input" name="login" required="required" pattern="[a-zA-Z0-9]{1,30}"/><br>
  <input type="password" id="pass-input" name="pass" required="required" pattern="[a-zA-Z0-9]{1,30}"/><br>
  <input type="submit" id="say-hello-button" value="Sign In" />

</form>
</body>
</html>