// json-parser.js

// Load the JSON file
const fs = require('fs');
const filePath = 'SampleTestCase.json';
const fileContent = fs.readFileSync(filePath, 'utf8');

// Parse the JSON file
const jsonData = JSON.parse(fileContent);

// Extract the keys object
const keys = jsonData.keys;
const n = keys.n;
const k = keys.k;

console.log(`n: ${n}, k: ${k}`);

// Extract the roots
const roots = {};
Object.keys(jsonData).forEach((key) => {
  if (key !== 'keys') {
    const root = jsonData[key];
    const base = parseInt(root.base, 10);
    const value = root.value;
    const decodedValue = parseInt(value, base);
    roots[key] = decodedValue;
  }
});

console.log('Roots:');
console.log(roots);
