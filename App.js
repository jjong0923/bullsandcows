import "./App.css";
import PlayGame from "./PlayGame";
import { useState } from "react";

function App() {
  const [mainShow, setMainShow] = useState(" ");
  const [playShow, setPlayShow] = useState("none");

  const handleShow = () => {
    setMainShow("none");
    setPlayShow(" ");
  };

  return (
    <div id="container">
      <div className="main-area" style={{ display: mainShow }}>
        <div className="main-box">
          <h1 className="text">숫자 야구 게임에 오신 것을 환영합니다.</h1>
          <button onClick={handleShow}>Play!</button>
        </div>
      </div>
      <div id="play" className="play-area" style={{ display: playShow }}>
        <PlayGame />
      </div>
    </div>
  );
}

export default App;
