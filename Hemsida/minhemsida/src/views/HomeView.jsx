import photo from '../assets/foto2.jpg'

export function HomeView({ language }) {
  return (
    <main>
      <h1>Lucas Kerslow</h1>
      <img src={photo} width="300" alt="Photo" />
      <p>Stockholm</p>

      <p>{language === "swe" ? 
      "Telefon: 073 051 08 14" 
      : 
      "Phone: +46 73 051 08 14"}</p>

      <p>{language === "swe" ? 
      <>Mail: <a href="mailto:lullek@hotmail.com">lullek@hotmail.com</a></>  
      : 
      <>Email: <a href="mailto:lullek@hotmail.com">lullek@hotmail.com</a></>}</p>

      <a href="https://www.linkedin.com/in/lucas-kerslow" target="_blank" rel="noopener noreferrer">LinkedIn</a>
    </main>
  );
}