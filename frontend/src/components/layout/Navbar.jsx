import { GiForkKnifeSpoon } from 'react-icons/gi'
import { Link } from 'react-router-dom'
import PropTypes from 'prop-types'

function Navbar({ title }) {
  return (
    <nav className='navbar mb-12 shadow-lg bg-neutral text-primary-content'>
      <div className='container mx-auto'>
        <div className='flex-none px-2 mx-2'>
          <GiForkKnifeSpoon className='inline pr-2 text-3xl' />
          <Link to='/' className='text-lg front-bold align-middle'>
            {title}
          </Link>
        </div>

        <div className='flex-1 px-2 mx-2'>
          <div className='flex justify-end'>
            <Link to='/' className='btn btn-ghost btn-sm rounded-btn'>
              Home
            </Link>
            <Link to='/about' className='btn btn-ghost btn-sm rounded-btn'>
              About
            </Link>
            <Link to='/login' className='btn btn-ghost btn-sm rounded-btn'>
              Login
            </Link>
            <Link to='/add' className='btn btn-ghost btn-sm rounded-btn'>
              Add Recipe
            </Link>
          </div>
        </div>
      </div>
    </nav>
  )
}

Navbar.defaultProps = {
  title: 'Recipe Finder',
}

Navbar.propTypes = {
  title: PropTypes.string,
}

export default Navbar
