import { useState } from 'react'

function App() {
  const [name, setName] = useState(null)
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)

  const fetchRandomUser = async () => {
    setLoading(true)
    setError(null)
    try {
      const res = await fetch('/api/users/random')
      if (!res.ok) {
        throw new Error(`Request failed with status ${res.status}`)
      }
      const data = await res.json()
      setName(data.name)
    } catch (err) {
      setError(err.message)
    } finally {
      setLoading(false)
    }
  }

  return (
    <main className="page">
      <div className="card">
        <h1>Hello World</h1>
        <p>Fetch a random user from the backend API.</p>

        <button className="btn" onClick={fetchRandomUser} disabled={loading}>
          {loading ? 'Loading...' : 'Get Random User'}
        </button>

        {name && <p className="result">👋 {name}</p>}
        {error && <p className="error">{error}</p>}
      </div>
    </main>
  )
}

export default App
