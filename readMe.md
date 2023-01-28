<h1>API Documentation</h1>


<h2>Publisher Module</h2>
<h3>Publisher Endpoint</h3>
<p>Publisher Request - <br>
{<br>
"firstName": string,<br>
"lastName": string,<br>
"bio": string,<br>
"avatar": byte array<br>
}</p>

<p>Publisher Response - <br>
    {<br>
    "id": uuid,<br>
    "firstName": string,<br>
    "lastName": string,<br>
    "bio": string,<br>
    "avatar": byte array,<br>
    "posts": list of post responses,<br>
    "created": timestamp,<br>
    "updated": timestamp,<br>
    "deleted": timestamp<br>
    }
</p>
<p>Publisher Update - <br>
{<br>
"firstName": string,<br>
"lastName": string,<br>
"bio": string,<br>
"avatar": byte array<br>
}
</p>
<p>Publisher Query - <br>
{<br>
"fullName": string,<br>
"page": int (default = 0),<br>
"size": int (default = 15)<br>
}
</p>


<ol>
    <li>
        <a>http://localhost:8040/api/v1/publisher</a>
        <p>Mapping - POST</p>
        <p>Description - Create a publisher</p>
        <p>Body - Publisher Request</p>
        <p>Response - Publisher Response</p>
    </li>
    <li>
        <a>http://localhost:8040/api/v1/publisher/{id}</a>
        <p>Mapping - GET</p>
        <p>Description - Get a publisher by id</p>
        <p>Path variables - id</p>
        <p>Response - Publisher Response</p>
    </li>
    <li>
        <a>http://localhost:8040/api/v1/publisher</a>
        <p>Mapping - PATCH</p>
        <p>Description - Update a publisher by id</p>
        <p>Request params - id</p>
        <p>Body - Publisher Update</p>
        <p>Response - Publisher Response</p>
    </li>
    <li>
        <a>http://localhost:8040/api/v1/publisher</a>
        <p>Mapping - GET</p>
        <p>Description - Search for a publisher</p>
        <p>Request params - Publisher Query</p>
        <p>Response - Array[Publisher Response]</p>
    </li>
    <li>
        <a>http://localhost:8040/api/v1/publisher</a>
        <p>Mapping - DELETE</p>
        <p>Description - Delete a publisher</p>
        <p>Request params - id<br>
        <p>Response - none</p>
    </li>
</ol>

<h3>Post Endpoint</h3>

<p>Post Request - <br>
{<br>
"publisherId": uuid,<br>
"headline": string,<br>
"image": byte array,<br>
"body": string,<br>
"link": string
<br>}
</p>
<p>Post Response - <br>
{<br>
    "id": uuid,<br>
    "headline": string,<br>
    "image": byte array,<br>
    "body": string,<br>
    "link": string,<br>
    "created": timestamp,<br>
    "updated": timestamp,<br>
    "deleted": timestamp
<br>}
</p>
<p>Post Update - <br>
{<br>
"headline": string,<br>
"image": byte array,<br>
"body": string,<br>
"link": string
<br>}
</p>
<p>Post Query - <br>
{
"headline": string,<br>
"postedDateMin": timestamp,<br>
"postedDateMax": timestamp<br>
}
</p>

<ol>
    <li>
        <a>http://localhost:8040/api/v1/post</a>
        <p>Mapping - POST</p>
        <p>Description - Create a post</p>
        <p>Body - Post Request</p>
        <p>Response - Post Response</p>
    </li>
    <li>
        <a>http://localhost:8040/api/v1/post/{id}</a>
        <p>Mapping - GET</p>
        <p>Description - Get a post by id</p>
        <p>Path variables - id</p>
        <p>Response - Post Response</p>
    </li>
    <li>
        <a>http://localhost:8040/api/v1/post</a>
        <p>Mapping - PATCH</p>
        <p>Description - Update a post by id</p>
        <p>Request params - id<br>
        <p>Body - Post Update</p>
        <p>Response - Post Response</p>
    </li>
    <li>
        <a>http://localhost:8040/api/v1/post</a>
        <p>Mapping - GET</p>
        <p>Description - Search for a post</p>
        <p>Request params - Post Query<br>
        <p>Response - Array[Post Response]</p>
    </li>
    <li>
        <a>http://localhost:8040/api/v1/post</a>
        <p>Mapping - DELETE</p>
        <p>Description - Delete a post</p>
        <p>Request params - id<br>
        <p>Response - none</p>
    </li>
</ol>


<h2>User Management</h2>
<h3>Token Endpoint</h3>
<ol>
    <li>
        <a>http://localhost:8030/api/v1/token</a>
        <p>Mapping - GET</p>
        <p>Description - generate tokens for the user</p>
        <p>Params - no params</p>
    </li>
</ol>
<h3>Permission Endpoint</h3>
<p>Permission Request - <br>
{<br>
"name": string
<br>}
<p>Permission Response - <br>
{<br>
"id": uuid,<br>
"name": string,<br>
"created": timestamp,<br>
"updated": timestamp,<br>
"deleted": timestamp
<br>}
</p>

<ol>
    <li>
        <a>http://localhost:8030/api/v1/permission</a>
        <p>Mapping - POST</p>
        <p>Description - Create a permission</p>
        <p>Body - Permission Request</p>
        <p>Response - Permission Response</p>
    </li>
    <li>
        <a>http://localhost:8030/api/v1/permission/{id}</a>
        <p>Mapping - GET</p>
        <p>Description - Get a permission by id</p>
        <p>Path variables - id<br>
        <p>Response - Permission Response</p>
    </li>
    <li>
        <a>http://localhost:8030/api/v1/permission</a>
        <p>Mapping - GET</p>
        <p>Description - Get permissions by id</p>
        <p>Request params - Array[id]<br>
        <p>Response - Array[Permission Response]</p>
    </li>
    <li>
        <a>http://localhost:8030/api/v1/permission</a>
        <p>Mapping - DELETE</p>
        <p>Description - Delete a post</p>
        <p>Request params - id<br>
        <p>Response - none</p>
    </li>
</ol>

<h3>Role Endpoint</h3>
<p>Role Request - <br>
{<br>
"name": string
"permissions": Array[uuid]
<br>}
<p>Role Response - <br>
{<br>
"id": uuid,<br>
"name": string,<br>
"created": timestamp,<br>
"updated": timestamp,<br>
"deleted": timestamp
<br>}
</p>

<ol>
    <li>
        <a>http://localhost:8030/api/v1/role</a>
        <p>Mapping - POST</p>
        <p>Description - Create a role</p>
        <p>Body - Role Request</p>
        <p>Response - Role Response</p>
    </li>
    <li>
        <a>http://localhost:8030/api/v1/role/{id}</a>
        <p>Mapping - GET</p>
        <p>Description - Get a permission by id</p>
        <p>Path variables - id<br>
        <p>Response - Role Response</p>
    </li>
    <li>
        <a>http://localhost:8030/api/v1/role</a>
        <p>Mapping - GET</p>
        <p>Description - Get roles by id</p>
        <p>Request params - Array[id]<br>
        <p>Response - Array[Role Response]</p>
    </li>
    <li>
        <a>http://localhost:8030/api/v1/role</a>
        <p>Mapping - DELETE</p>
        <p>Description - Delete a role</p>
        <p>Request params - id<br>
        <p>Response - none</p>
    </li>
</ol>

<h3>UM Publisher Endpoint</h3>
<p>Publisher Request - <br>
{<br>
"email": string,<br>
"password": string
<br>}
<p>Publisher Response - <br>
{<br>
"id": uuid,<br>
"email": string,<br>
"created": timestamp,<br>
"updated": timestamp,<br>
"deleted": timestamp
<br>}
</p>

<ol>
    <li>
        <a>http://localhost:8030/api/v1/publisher</a>
        <p>Mapping - POST</p>
        <p>Description - Create a publisher</p>
        <p>Body - Publisher Request</p>
        <p>Response - Publisher Response</p>
    </li>
    <li>
        <a>http://localhost:8030/api/v1/publisher/{id}</a>
        <p>Mapping - GET</p>
        <p>Description - Get a publisher by id</p>
        <p>Path variables - id<br>
        <p>Response - Publisher Response</p>
    </li>
    <li>
        <a>http://localhost:8030/api/v1/publisher</a>
        <p>Mapping - GET</p>
        <p>Description - Get publisher by id</p>
        <p>Request params - Array[id]<br>
        <p>Response - Array[Publisher Response]</p>
    </li>
    <li>
        <a>http://localhost:8030/api/v1/publisher</a>
        <p>Mapping - DELETE</p>
        <p>Description - Delete a publisher</p>
        <p>Request params - id<br>
        <p>Response - none</p>
    </li>
</ol>
<h3>Login Endpoint</h3>
<p>Login Request - <br>
{<br>
"email": string,<br>
"password": string
<br>}
<p>Login Response - <br>
{<br>
"accessToken": string,<br>
"refreshToken": string
<br>}
</p>
<ol>
    <li>
        <a>http://localhost:8030/api/v1/login</a>
        <p>Mapping - POST</p>
        <p>Description - Sign in</p>
        <p>Body - Login Request</p>
        <p>Response - Login Response</p>
    </li>
</ol>