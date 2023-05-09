function About() {
  return (
    <>
      <h1 className='text-6xl mb-4'>Recipe Finder</h1>
      <p className='mb-4 text-2xl font-light'>
        Recipe Finder is a search engine focusing on food recipes; enter the
        ingredients you already have at home. After that, we will tell you what
        varieties of foods you can make using those ingredients. The project was
        created by
        <strong>
          <a href='https://traversymedia.com'>
            {' '}
            Akbar Sayakov, Anushree Shah, Kevin Lam
          </a>
        </strong>
        .
      </p>
      <p className='text-lg text-gray-400'>
        Version <span className='text-white'>1.0.0</span>
      </p>
    </>
  )
}

export default About
