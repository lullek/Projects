export function shufflePokemons(alternatives){
    for (let i = alternatives.length - 1; i > 0; i--){
        let j = Math.floor(Math.random()*(i+1));
        [alternatives[i], alternatives[j]] = [alternatives[j], alternatives[i]];
    }
    return alternatives;
}

export function compareHighScore(highScoreA, highScoreB){
    if(highScoreA.data.playerHighScore > highScoreB.data.playerHighScore){
        return -1;
    }
    if(highScoreA.data.playerHighScore < highScoreB.data.playerHighScore){
        return 1;
    }
    return 0;
}

export function sortPersonalHighScore(highscores){
    return[...highscores].sort(compareHighScore);
}