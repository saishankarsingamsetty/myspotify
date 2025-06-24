<!DOCTYPE html>
<html>
<head>
    <title>Welcome to MySpotify App</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(to right, #1db954, #191414);
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            color: white;
        }

        .container {
            text-align: center;
            background-color: rgba(0,0,0,0.6);
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.3);
        }

        h1 {
            margin-bottom: 30px;
        }

        .login-button {
            padding: 12px 24px;
            background-color: #1DB954;
            color: white;
            border: none;
            border-radius: 30px;
            font-size: 16px;
            cursor: pointer;
            text-decoration: none;
            font-weight: bold;
        }

        .login-button:hover {
            background-color: #169c44;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to MySpotify App</h1>
        <a href="/profile" class="login-button">Login with Spotify</a>
    </div>
</body>
</html>
