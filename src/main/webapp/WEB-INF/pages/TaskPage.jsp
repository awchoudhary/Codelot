<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Task Page</title>
</head>
<body>
    <div>
        <form method="post">
            <textarea rows="4" cols="50" name="source"></textarea>
            <button style="padding: 10px; color: black" title="Execute" type="submit" onclick="form.action='/execute';">Execute</button>
        </form>
        <textarea rows="4" cols="50" name="response"></textarea>
    </div>
</body>
</html>