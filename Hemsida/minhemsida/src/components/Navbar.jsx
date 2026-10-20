import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import lk from "../../public/LK.png";
import "../styles/Navbar.css";

export function Navbar({ language, toggleLanguage }) {
  const [menuOpen, setMenuOpen] = useState(false);
  const closeMenu = () => setMenuOpen(false);
  const navigate = useNavigate();

  return (
    <nav className="navbar">
      <img className="navbar-icon" src={lk} alt="LK" width="30" onClick={() => {navigate("/"); closeMenu();}}/>
      <button className="hamburger" onClick={() => setMenuOpen(!menuOpen)} aria-label={menuOpen ? "Stäng meny" : "Öppna meny"} aria-expanded={menuOpen} aria-controls="navbar-links">
        {menuOpen ? "✕" : "☰"}
      </button>
      <div id="navbar-links" className={`navbar-links ${menuOpen ? "open" : ""}`}>
        <Link to="/" onClick={closeMenu}>{language === "swe" ? "Hem" : "Home"}</Link>
        <Link to="/about" onClick={closeMenu}>{language === "swe" ? "Om mig" : "About me"}</Link>
        <Link to="/education" onClick={closeMenu}>{language === "swe" ? "Utbildning" : "Education"}</Link>
        <Link to="/skills" onClick={closeMenu}>{language === "swe" ? "Kompetenser" : "Skills"}</Link>
        <Link to="/projects" onClick={closeMenu}>{language === "swe" ? "Projekt" : "Projects"}</Link>
        <Link to="/experience" onClick={closeMenu}>{language === "swe" ? "Erfarenhet" : "Experience"}</Link>
        <Link to="/quiz" onClick={closeMenu}>{language === "swe" ? "Pokémonquiz" : "Pokémon quiz"}</Link>
        <button onClick={() => {toggleLanguage(); closeMenu()}}> {language === "swe" ? "English" : "Svenska"} </button>
      </div>
    </nav>
  );
}