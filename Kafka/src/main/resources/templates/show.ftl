<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>show</title>
</head>
<body>
    <a type="text" id="name" name="name">show</a>
</body>

<#list listComment as c>
    <h1 >${c.comment}</h1><br/>
</#list>

</html>