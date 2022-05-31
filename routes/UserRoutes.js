const UserController = require('../controller/UserController');
const verifyToken = require('../middleware/JWTverify');

const router = require('express').Router();

router.post('/userRegister', UserController.userRegister);
router.get('/getAllUser', UserController.getAllUser);
router.get('/getUserByUsername', verifyToken, UserController.getByUsername);
router.put('/updateUser', verifyToken, UserController.updateUser);
router.put('/changePassword', verifyToken, UserController.changePassword);
module.exports = router;