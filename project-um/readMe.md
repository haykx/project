<h1>API Documentation</h1>


<h2>Publisher Module</h2>

<ol>
    <li>
        <a>http://localhost:8040/api/v1/publisher</a>
        <p>Mapping - POST</p>
        <p>Description - Create a publisher</p>
        <p>Body - <br>
        <p>{<br>
            "firstName": string,<br>
            "lastName": string,<br>
            "bio": string,<br>
            "avatar": byte array<br>
            }</p>
        </p>
        <p>Response - <br>
        <p>{<br>
            "id": uuid,<br>
            "firstName": string,<br>
            "lastName": string,<br>
            "bio": string,<br>
            "avatar": byte array,<br>
            "created": timestamp,<br>
            "posts": list of post responses,<br>
            "updated": timestamp,<br>
            "deleted": timestamp<br>
            }</p>
        </p>
        </p>
    </li>
    <li>
        <a>http://localhost:8040/api/v1/publisher</a>
        <p>Mapping - GET</p>
        <p>Description - Get a publisher</p>
        <p>Body - <br>
        <p>{<br>
            "firstName": string,<br>
            "lastName": string,<br>
            "bio": string,<br>
            "avatar": byte array<br>
            }</p>
        </p>
        <p>Response - <br>
        <p>{<br>
            "id": uuid,<br>
            "firstName": string,<br>
            "lastName": string,<br>
            "bio": string,<br>
            "avatar": byte array,<br>
            "created": timestamp,<br>
            "posts": list of post responses,<br>
            "updated": timestamp,<br>
            "deleted": timestamp<br>
            }</p>
        </p>
        </p>
    </li>
</ol>




<h2>User Management</h2>
<ol>
    <li>
        <a>http://localhost:8030/api/v1/token</a>
        <p>Mapping - GET</p>
        <p>Description - generate tokens for the user</p>
        <p>Params - no params</p>
    </li>
    <li>
        <a>http://localhost:8030/api/v1/permission</a>
        <p>Mapping - POST</p>
        <p>Description - create a permission</p>
        <p>Params - json<br>
        {"name": name}</p>
    </li>
    <li>
        <a>http://localhost:8030/api/v1/permission</a>
        <p>Mapping - GET</p>
        <p>Description - generate tokens for the user</p>
        <p>Params - no params</p>   
    </li>
</ol>
