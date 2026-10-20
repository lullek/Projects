import { useState } from "react";
import { Link } from "react-router-dom";
import "../styles/Navbar.css";

export function Navbar({ language, toggleLanguage }) {
  const [menuOpen, setMenuOpen] = useState(false);
  const closeMenu = () => setMenuOpen(false);

  return (
    <nav className="navbar">
      <Link to="/">{language === "swe" ? "Hem" : "Home"}</Link>
      <Link to="/about">{language === "swe" ? "Om mig" : "About me"}</Link>
      <Link to="/education">{language === "swe" ? "Utbildning" : "Education"}</Link>
      <Link to="/skills">{language === "swe" ? "Kompetenser" : "Skills"}</Link>
      <Link to="/projects">{language === "swe" ? "Projekt" : "Projects"}</Link>
      <Link to="/experience">{language === "swe" ? "Erfarenhet" : "Experience"}</Link>
      <Link to="/quiz">{language === "swe" ? "Pokémonquiz" : "Pokémon quiz"}</Link>

      <button onClick={toggleLanguage}> {language === "swe" ? "English" : "Svenska"} </button>
    </nav>
  );
}