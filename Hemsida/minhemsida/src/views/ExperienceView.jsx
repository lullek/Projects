export function ExperienceView({ language }) {
  return (
    <main>
      <h1>{language === "swe" ? "Yrkeserfarenhet" : "Professional Experience"}</h1>
      <p>{language === "swe" ? 
      <b>KTH:s IT-avdelning</b>
      : 
      <b>KTH IT Department</b>}</p>
      <p>{language === "swe" ? 
      "• Felsökning och support i komplex IT-miljö för studenter och anställda"
      : 
      "• Troubleshooting and technical support in a complex IT environment"}</p>
      <p>{language === "swe" ? 
      "• Kontohantering och behörighetsstyrning"
      : 
      "• Account management and access control"}</p>
      <p>{language === "swe" ? 
      "• Administration av mailboxar"
      : 
      "• Mailbox administration"}</p>
      <p>{language === "swe" ? 
      "• Arbete i ärendehanteringssystem (Edge)"
      : 
      "• Working with the Edge ticket management system"}</p>
      <p>{language === "swe" ? 
      "• Servicebemötande i telefonsamtal och ansikte mot ansikte"
      : 
      "• Support through phone and face-to-face interactions"}</p>

      <p>2022 - 2026</p>
      <br />


      <p>{language === "swe" ? 
      <b>Arbeten inom service, vård och skola</b>
      : 
      <b>Service, Healthcare and Education</b>}</p>
      <p>{language === "swe" ? 
      "2018 - 2019: TBA-tränare på Ikasus friskola"
      : 
      "2018 - 2019: TBA Instructor at Ikasus Independent School"}</p>
      <p>{language === "swe" ? 
      "2013 - 2018: Boendestödjare på Sommaralléns Serviceboende"
      : 
      "2013 - 2018: Residential Support Worker at Sommarallén"}</p>
      <p>{language === "swe" ? 
      "2009 - 2013: Boendeassistent på Ikasus korttidshem"
      : 
      "2009 - 2013: Residential Care Assistant at Ikasus Short-Term Care"}</p>
      <p>{language === "swe" ? 
      "2008: Barista på Espresso House"
      : 
      "2008: Barista at Espresso House"}</p>
      <p>{language === "swe" ? 
      "• Erfarenheterna har gett vana av ansvar, struktur, dokumentation, samarbete och professionellt bemötande."
      : 
      "• Developed skills in responsibility, structure, documentation, collaborating and communicating professionally."}</p>

      <p>2008 - 2019</p>
    </main>
  );
}