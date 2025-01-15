<h2>컴포넌트 분리</h2>
<p>
  기존 게임 플레이 기능을 컴포넌트로 분리 후 App.js 수정
</p>
<h2>현재 게임 history 표시 기능</h2>
<p>
  왼쪽 중앙에 테이블로 history 표시
</p>
<p>
  showHistory state 생성 후 PlayGame 함수에서 콜백 state를 사용
</p>
<h3>
  const newHistory = [...prevHistory, {inputs : [...inputs], strikeCount, ballCount}];
</h3>
<p>
  inputs:[...inputs] -> [...inputs] 객체 속성 이름을 inputs로 저장을 하고<br>
  만약 "inputs:"가 없다면 속성 이름 없이 단순히 배열만 복사하기 때문에 X
</p>

