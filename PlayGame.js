import Button from "./Button";
import { useState } from "react";
import "./PlayGame.css";

let base = [];
let tryCount = 0;

function PlayGame() {
  const [messgae, setMessage] = useState("3개의 숫자를 입력해주세요.");
  const [display, setDisplay] = useState(false);
  const [inputs, setInputs] = useState([0, 0, 0]);
  const [showHistory, setShowHistory] = useState([]);

  const clearGame = () => {
    base = [];
    tryCount = 0;
    setMessage("3개의 숫자를 입력해주세요.");
    setInputs([0, 0, 0]);
    setDisplay(false);
    setShowHistory([]);
  };

  function setBaseNumber() {
    let setBase = [];
    while (setBase.length < 3) {
      let r = Math.floor(Math.random() * (9 - 1) + 1);
      if (!setBase.includes(r)) {
        setBase.push(r);
      }
    }
    return setBase;
  }

  const handleInputChange = (index, value) => {
    const newInputs = [...inputs];
    newInputs[index] = parseInt(value, 10);
    setInputs(newInputs);
  };

  function playGameRound(input) {
    if (tryCount === 0) {
      base = setBaseNumber();
    }
    let strikes = 0;
    let balls = 0;

    for (let i = 0; i < 3; i++) {
      for (let j = 0; j < 3; j++) {
        if (base[i] === input[i] && i === j) {
          strikes++;
        } else if (base[i] === input[j] && i !== j) {
          balls++;
        }
      }
    }
    tryCount++;

    return { strikes, balls };
  }

  const playGame = () => {
    const { strikes, balls } = playGameRound(inputs);
    let strikeCount = strikes;
    let ballCount = balls;

    setShowHistory((prevHistory) => {
      const newHistory = [...prevHistory, {inputs : [...inputs], strikeCount, ballCount}];
      return newHistory;
    });

    setMessage(`스트라이크 : ${strikeCount} \n볼 : ${ballCount}`);
    setInputs([0, 0, 0]);

    if (strikeCount === 3) {
      setDisplay(true);
    } else if (strikeCount === 0 && ballCount === 0) {
      setMessage("OUT!");
    }
  };

  return (
    <div className="play-box">
      <div className="text-box">
        <h1 className="text1">{messgae}</h1>
      </div>
      <div className="show-history">
        <table>
          <tr>
            <th>횟수</th>
            <th>숫자리스트</th>
            <th>구분</th>
          </tr>
          {showHistory
            .map((item, i) => {
              return (
                <tr key={i}>
                  <td>{i + 1}</td>
                  <td>{item.inputs.join(", ")}</td>
                  <td>{item.strikeCount} / {item.ballCount}</td>
                </tr>
              );
            })}
        </table>
      </div>
      <div className="inputs">
        {inputs.map((value, index) => (
          <input
            key={index}
            className="input"
            type="number"
            value={value}
            onChange={(e) => handleInputChange(index, e.target.value)}
            required
          />
        ))}
      </div>
      <div className="button-box">
        <Button value={"입력"} onClick={playGame} className={"button"}></Button>
        <Button
          value={"재시작"}
          onClick={clearGame}
          className={"button"}
        ></Button>
      </div>
      {display && (
        <div id="clearText">
          <h1>축하드립니다!</h1>
          <p>{`${tryCount}번만에 맞추셨습니다!!`}</p>
        </div>
      )}
    </div>
  );
}

export default PlayGame;
