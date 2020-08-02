import modeOptions from './modeOptions'

function render (mode) {
  const rootElement = document.querySelector(':root')
  const options = modeOptions[mode]

  for (const k in options) {
    rootElement.style.setProperty(k, options[k])
  }
}

/**
 * Sets a color scheme for the website.
 * If browser supports "prefers-color-scheme", 'auto' mode will respect the setting for light or dark mode
 * otherwise it will set a dark theme during night time
 */
export default function applyMode (mode) {
  if (mode !== 'auto') {
    render(mode)
    return
  }

  const isDarkMode = window.matchMedia('(prefers-color-scheme: dark)').matches
  const isLightMode = window.matchMedia('(prefers-color-scheme: light)').matches

  if (isDarkMode) render('dark')
  if (isLightMode) render('light')

  if (!isDarkMode && !isLightMode) {
    console.log('You specified no preference for a color scheme or your browser does not support it. I schedule dark mode during night time.')
    const hour = new Date().getHours()
    if (hour < 6 || hour >= 18) render('dark')
    else render('light')
  }
}
