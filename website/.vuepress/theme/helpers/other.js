export function getOneColor () {
  const tagColorArr = [
    '#e15b64',
    '#f47e60',
    '#f8b26a',
    '#abbd81',
    '#849b87',
    '#e15b64',
    '#f47e60',
    '#f8b26a',
    '#f26d6d',
    '#67cc86',
    '#fb9b5f',
    '#3498db'
  ]
  const index = Math.floor(Math.random() * tagColorArr.length)
  return tagColorArr[index]
}
