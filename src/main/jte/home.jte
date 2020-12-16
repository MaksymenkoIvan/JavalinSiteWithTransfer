<!DOCTYPE html>

@param org.example.User user

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        body{
            background: url(https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJrAQw5D1NMqGPoDVwI9Wk6SAClbgA0Hz0pA&usqp=CAU);
        }
        .navigation{
            position: fixed;
            top: 7%;
            left: 10px;
        }
        ul.nav{
                margin-left: 0px;
                padding-left: 0px;
                list-style: none;
        }
        ul.nav a {
            background: url(https://image.freepik.com/free-vector/speed-and-motion-futuristic-blue-background_23-2148400715.jpg);
            display: block;
            width: 7em;                padding:10px;
            background-color: #f4f4f4;
            border-top: 1px dashed #333;
            border-right: 1px dashed #333;
            border-left: 5px solid #333;
            text-decoration: none;
            color: white;
        }
        ul.nav li:last-child a {
            border-bottom: 1px dashed #333;
        }
        .tab{
            position: absolute;
            left: 20%;
            top: 60px;
        }
        .tab{
            border: 1px;
            border-color: black;
        }
        td{
            font-size: 40px;
            color: white;
        }

		.search-form{
			margin-top: 240px;
			position: relative;
			left: 39%;
			width: 300px;
            height: 300px;
			align-items: center;

		}
        .serach-form__wrap{
            border-color: transparent;
        }

		.search-form__info{
			display: flex;
			flex-wrap: wrap;
		}

		.login{
			color: white;
			margin-bottom: 10px;
			font-size: 30px;
		}

		.pass{
			color: white;
			margin-bottom: 10px;
			font-size: 30px;
		}

		.logpass_field{
			height: 40px;
			background-color: transparent;
			width: 300px;
			color: white;
		}

		.logpass_field::placeholder{
			font-size: 22px;
			color: white;
		}

		.button{
			margin-top: 10px;
			font-size: 30px;
			border: 0;
			margin-top: 10px;
			color: black;
		}

		.but-signup{
			position: relative;
            padding: 5px 26px;
            text-transform: uppercase;
            font-weight: 500;
            height: 45px;
            font-size: 24px;
            color: white;
            background: #1B7DFF;
            border: 0;
            margin-top: 10px;
            left: 60px;
		}

		.logpass_field{
			height: 40px;
			background-color: transparent;
			width: 300px;
			color: black;
		}
    </style>
</head>
<body>
<div class="navigation">
    <ul class="nav">
        <li><a href="#">Главная</a></li>
        <li><a href="#">Мои счета</a></li>
        <li><a href="#">Моя информация</a></li>
        <li><a href="#">Настройки</a></li>
        <li><a href="#">Выход</a></li>
    </ul>
</div>
<div class="table">
    <table class="tab">

        <tr>
            <td>Name: </td>
        </tr>
        <tr>
            <td>ID: ${user.getId()}</td>
        </tr>
        <tr>
            <td>Balance: ${user.getBalance()}</td>
        </tr>

    </table>
    <form class="search-form" method="POST" action="/api/transfer/">
        <fieldset class="serach-form__wrap">
            <p class="login">id</p>
            <input name="id" type="text" class="logpass_field" placeholder="Введите id">
            <p class="pass">Amount</p>
            <input name="amount" type="text" class="logpass_field" placeholder="Введите Сумму">
            <p class="signup"><input type="submit" class="but-signup" value="Send"></p>
        </fieldset>
    </form>
</div>
</body>
</html>