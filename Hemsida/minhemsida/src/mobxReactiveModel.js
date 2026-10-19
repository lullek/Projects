import { observable, reaction } from "mobx";
import { model } from "/src/PokemonModel.js";

export const reactiveModel= observable(model);

window.myModel= reactiveModel;

function checkCurrentPokemonIdACB() {
    return reactiveModel.currentPokemonId;
}
function updateCurrentPokemonIdACB() {
    return reactiveModel.currentIdEffect();
}

reaction(checkCurrentPokemonIdACB, updateCurrentPokemonIdACB);

reactiveModel.updatePokemonId(reactiveModel.generateRandomId());