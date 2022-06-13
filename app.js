const express = require('express');
const app = express();
const bodyParser = require('body-parser');
// const {sequilize} = require('./model');
const {sequelize} = require('./model');
require('dotenv').config();

app.get('/', (req, res) => res.send('Welcome to SkinGorithm API'));

sequelize.authenticate().then(() => console.log("NodeJS Responding Successfully"));
app.use(express.urlencoded({extended: true}));
app.use(bodyParser.json({limit:'50mb'}))
app.use(express.json());

const IngredientRoute = require('./routes/IngredientRoutes')
app.use('/Ingredient', IngredientRoute);

const UserRoute = require('./routes/UserRoutes')
app.use('/User', UserRoute);

const LoginRoute = require('./routes/LoginRoutes')
app.use('/Login', LoginRoute)

const NotifyRoute = require('./routes/NotifyRoutes')
app.use('/Alarm', NotifyRoute)

const HistoryRoute = require('./routes/HistoryRoutes')
app.use('/History', HistoryRoute)

app.listen(process.env.PORT, () => console.log(`Berjalan di Port ${process.env.PORT}`));