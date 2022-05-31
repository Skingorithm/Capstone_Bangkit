const Seq = require('sequelize');
module.exports = (sequelize) => {
    const Ingredient = sequelize.define(
        "Ingredient",
        {
            IngredName: {type: Seq.STRING, primaryKey: true},
            IngredFunction: {type: Seq.STRING, allowNull: false},
            IngredEffect: {type: Seq.STRING, allowNull: false},
        },
        {
            freezeTableName: true,
            timestamp: false
        }
    );
    return Ingredient;
}