const express = require('express');
const app = express();
// const {sequilize} = require('./model');
const {sequelize} = require('./model');
require('dotenv').config();

app.get('/', (req, res) => res.send('Welcome to SkinGorithm API'));

sequelize.authenticate().then(() => console.log("NodeJS Responding Successfully"));
app.use(express.urlencoded({extended: true}));
app.use(express.json());

const IngredientRoute = require('./routes/IngredientRoutes')
app.use('/Ingredient', IngredientRoute);

const UserRoute = require('./routes/UserRoutes')
app.use('/User', UserRoute);

const LoginRoute = require('./routes/LoginRoutes')
app.use('/Login', LoginRoute)

const NotifyRoute = require('./routes/NotifyRoutes')
app.use('/Alarm', NotifyRoute)

app.listen(5000, () => console.log('Berjalan di port 5000'));