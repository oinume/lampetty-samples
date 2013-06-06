<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<title>フォーム</title>
<#include "/common/head-css.ftl" />
</head>
<body>
<div class="container">
<@spring.bind "userForm.name" />
<form method="POST" action="/form/process">
<fieldset>
<legend>フォーム</legend>
<label>User name</label>
<input type="text" name="${spring.status.expression}" placeholder="your name" value="${spring.status.value?default("")}">
<@spring.showErrors "<br>", "color:red" />
<button type="submit" class="btn">Submit</button>
</fieldset>
</form>
</div>
</body>
</html>