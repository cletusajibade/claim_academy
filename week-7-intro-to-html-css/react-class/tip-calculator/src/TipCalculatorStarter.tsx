// TipCalculatorStarter.tsx — YOUR lab file. 🧪
//
// Follow the steps in README.md (same folder). Save after each step:
//

// STEP 1: import useState (and the ChangeEvent type) from 'react'
import { useState } from 'react';
import type { ChangeEvent } from 'react';

// STEP 3: create a TipPercent type (10 | 15 | 20) and a TIP_OPTIONS array
type TipPercent = 10 | 15 | 20;
const TIP_OPTIONS: TipPercent[] = [10, 15, 20];

// STEP 2: write formatMoney(amount: number): string
function formatMoney(amout: number): string {
  return `$${amout.toFixed(2)}`;
}

export default function TipCalculatorStarter() {
  // STEP 1: a `bill` state (a string) and a handleBillChange function
  const [bill, setBill] = useState('');

  function handleBillChange(e: ChangeEvent<HTMLInputElement>) {
    setBill(e.target.value);
  }

  // STEP 3: a `tipPercent` state of type TipPercent
  const [tipPercent, setTipPercent] = useState<TipPercent>(15);

  // STEP 4: a `people` state (a number, starting at 1)

  // STEP 2: derived values — billAmount, tip, total (STEP 4: perPerson)
  const billAmount = Number(bill) || 0;
  const tip = (billAmount * tipPercent) / 100;
  const total = billAmount + tip;

  return (
    <div className="stack">
      <p>
        Start with Step 1 in README.md. Replace this paragraph with your
        calculator!
      </p>
      {/* STEP 1: the bill <input> */}
      <label>
        Bill amount ($):{' '}
        <input type="number" value={bill} onChange={handleBillChange} />
      </label>
      <p>Tip: {formatMoney(tip)}</p>
      <p>Total: {formatMoney(total)}</p>

      {/* STEP 3: the tip buttons */}
      <div className="row">
        <span>Tip:</span>
        {TIP_OPTIONS.map((percent) => (
          <button
            key={percent}
            className={percent === tipPercent ? '' : 'secondary'}
            onClick={() => setTipPercent(percent)}
          >
            {percent}%
          </button>
        ))}
      </div>

      {/* STEP 4: the people − / + buttons */}
      {/* STEP 2: show the tip and total */}
      {/* STEP 5: a Reset button */}
    </div>
  );
}
