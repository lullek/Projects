function getPokeACB(response){ 
    if(response.status!=200)
      throw new Error("could not find pokemon " + response.status)
    return response.json(); 
   }

function nameACB(result) {
    return result.name.charAt(0).toUpperCase() + result.name.slice(1);
}

function imageACB(result) {
    return result.sprites.other["official-artwork"].front_default;
}

export function getPokemon(id){
    return fetch("https://pokeapi.co/api/v2/pokemon/"+id).then(getPokeACB);
}

export async function getPokemonName(id){
    const nameInfo = await getPokemon(id).then(nameACB);
    return nameInfo;
}

export async function getPokemonImage(id){
    const imageInfo = await getPokemon(id).then(imageACB);
    return imageInfo;
}