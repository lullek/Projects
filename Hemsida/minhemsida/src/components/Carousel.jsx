import { useState } from "react";
import { Link } from "react-router-dom";
import adungeonadventure from "../assets/adungeonadventure.png";
import otherworldguardian from "../assets/otherworldguardian.png";
import webbsidor from "../assets/webbsidor.png";
import "../styles/Carousel.css";

const items = [
  { 
    id: 1, 
    title: {
      swe: "A Dungeon Adventure",
      eng: "A Dungeon Adventure",
    },
    image: adungeonadventure, 
    text: {
      swe: "Det här är ett spel som jag har skapat, där du utforskar en dungeon och löser pussel. Jag har utvecklat spelet från grunden i Unity och använt assets från Unity Asset Store. Jag kan visa kodexempel om så önskas. Spelet kan laddas ner här:",
      eng: "This is a game I made, where you explore a dungeon and solve puzzles. I have built the game in Unity from scratch using assets from the Unity Asset Store for graphics. I can show code samples if desired. The game can be downloaded here:",
    }, 
    link: "https://drive.google.com/drive/folders/13xloZEoqy9XVV3wkxqdQ_mvWV3fuazrv",
    internal: false,
    linktext: {
      swe: "Ladda ner",
      eng: "Download",
    }
  },
    
  { 
    id: 2, 
    title: {
      swe: "Otherworld Guardian",
      eng: "Otherworld Guardian",
    }, 
    image: otherworldguardian,
    text: {
      swe: "Det här är ett spel som jag utvecklade tillsammans med en grupp som projekt i kursen DH2650 på KTH. Arbetet delades upp mellan gruppmedlemmarna, och jag och en annan person ansvarade för spelprogrammeringen. Jag stod för all programmering av utforskningsdelen av spelet, men inte för pusseldelen inuti portalen. Spelet kan laddas ner från webbplatsen:",
      eng: "This is a game I made with a group as a project for the course DH2650 at KTH. The different tasks were divided between the group members, and me and another person were responsible for the game programming. I have done all the programming for the exploration part of the game (not the puzzle part inside the portal). The game can be downloaded from the website:",
    },
    link: "https://otherworld-guardian.vercel.app/",
    internal: false,
    linktext: {
      swe: "Få reda på mer",
      eng: "Find out more",
    } 
  },

  {
    id: 3,
    title:  {
      swe: "Websidor",
      eng: "Websites",
    },
    image: webbsidor,
    text: {
      swe: "Jag har utvecklat webbsidor i JavaScript och React. Denna webbsida är ett exempel. Ett annat exempel är ett gruppprojekt där jag hade som uppgift att implementera ett Pokémonquiz som hämtar information från ett externt API med hjälp av promises. Jag har inkluderat detta quiz på den här webbsidan, och det kan hittas här:",
      eng: "I have developed websites using JavaScript and React. This website is one example. Another example is a group project in which I was tasked with implementing a Pokémon quiz that fetches data from an external API using promises. I have included the quiz on this website, and it can be found here:",
    },
    link: "/quiz",
    internal: true,
    linktext: {
        swe: "Pokémonquiz",
        eng: "Pokémon quiz",
    }
  },
];

export function Carousel(props) {
  const [currentIndex, setCurrentIndex] = useState(0);
  const [direction, setDirection] = useState(1);
  const [animating, setAnimating] = useState(false);

  const changeItem = (dir) => {
    if (animating) return;

    setDirection(dir);
    setAnimating(true);

    setTimeout(() => {
      setCurrentIndex((index) =>
        (index + dir + items.length) % items.length
      );
      setAnimating(false);
    }, 250);
  };

  const item = items[currentIndex];

  return (
    <div className="carousel">
      <div className="carousel-content">
        <button
          onClick={() => changeItem(-1)}
          aria-label="Föregående"
        >
          ‹
        </button>

        <div
          className={`carousel-item ${
            animating
              ? direction === 1
                ? "slide-out-left"
                : "slide-out-right"
              : ""
          }`}
        >
          <h2>{item.title[props.language]}</h2>
          <img src={item.image} alt={item.title} />
          <p>{item.text[props.language]}</p>
          {item.internal ? (
            <Link to={item.link}>
            {item.linktext[props.language]}
            </Link>
          ) : (
            <a
              href={item.link}
              target="_blank"
              rel="noopener noreferrer"
            >
            {item.linktext[props.language]}
            </a>
          )}
        </div>

        <button
          onClick={() => changeItem(1)}
          aria-label="Nästa"
        >
          ›
        </button>
      </div>

      <div className="carousel-dots">
        {items.map((item, index) => (
          <button
            key={item.id}
            className={
              index === currentIndex ? "dot active" : "dot"
            }
            onClick={() => {
              if (!animating && index !== currentIndex) {
                setDirection(index > currentIndex ? 1 : -1);
                setCurrentIndex(index);
              }
            }}
            aria-label={`Visa objekt ${index + 1}`}
            aria-current={
              index === currentIndex ? "true" : undefined
            }
          />
        ))}
      </div>
    </div>
  );
}




