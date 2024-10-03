
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            body {
                padding: 20px;
            }
            table {
                width: 100%;
                border-collapse: collapse;
            }
            th, td {
                padding: 8px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }
            th {
                background-color: #f2f2f2;
            }
            .form-control {
                width: 100%;
            }
            .mt-4 {
                margin-top: 20px;
            }
        </style>
    </head>
    <body>

    <c:if  test="${sessionScope.LOGIN_USER == null || sessionScope.LOGIN_USER.roleID ne 'AD'}">
        <c:redirect url="login.html"></c:redirect>
    </c:if>

    <h1 class="mt-4">Welcome: ${sessionScope.LOGIN_USER.fullName}</h1>

    <a href="shop.html" class="btn btn-danger mt-2">Logout</a>

    <form method="POST" class="mt-4 mb-4">
        <div class="input-group">
            <input type="text" name="search" value="${param.search}" class="form-control" placeholder="Search...">
            <div class="input-group-append">
                <button type="submit" name="action" value="Search" class="btn btn-primary">Search</button>
            </div>
        </div>
    </form>

    <c:if test="${requestScope.LIST_USER != null}">
        <c:if test="${not empty requestScope.LIST_USER}">
            <table class="table table-bordered table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">No</th>
                        <th scope="col">User ID</th>
                        <th scope="col">Full Name</th>
                        <th scope="col">Role ID</th>
                        <th scope="col">Password</th>
                        <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="user" varStatus="counter" items="${requestScope.LIST_USER}">
                    <form action="MainController">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${user.userID}</td>
                            <td><input type="text" name="fullName" value="${user.fullName}" class="form-control"></td>
                            <td><input type="text" name="roleID" value="${user.roleID}" class="form-control"></td>
                            <td>${user.password}</td>
                            <td>
                                <a href="" class="btn btn-danger">Delete</a>
                                <button type="submit" name="action" value="Update" class="btn btn-primary">Update</button>
                                <input type="hidden" name="userID" value="${user.userID}">
                                <input type="hidden" name="search" value="${param.search}">
                            </td>
                        </tr>
                    </form>
                </c:forEach>
                </tbody>
            </table>
            <h1 class="mt-4">

                ${requestScope.MESSAGE}
            </h1>
        </c:if>
    </c:if>



</body>
</html>
