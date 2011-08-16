#ifndef _complex_h_
#define _complex_h_

#include <iostream>

/************************************************
 * Complex class
 *
 * Members defined
 *		complex()
 *		complex(real, imaginary)
 *		complex(complex)
 *
 *		realpart()
 *		imaginarypart()
 *
 *		set(real, imaginary)
 *		set_real(real)
 *		set_imaginary(imaginary)
 *
 * Operator member functions
 *		c = c
 *		c += c
 *		c += s
 *		c -= c
 *		c -= s
 *		c *= c
 *		c *= s
 *		c /= c
 *		c /= s
 *
 *		++c
 *		--c
 *
 *		c = c + c
 *		c = c + s
 *		c = s + c
 *		c = c - c
 *		c = c - s
 *		c = s - c
 *		c = c * c
 *		c = c * s
 *		c = s * c
 *		c = c / c
 *		c = c / s
 *		c = s / c
 *
 *		ostream << c
 *		istream >> c
 *************************************************/
class complex {
	private:
		double real;
		double imaginary;
	public:
		complex(void) {
			real = 0;
			imaginary = 0;
		}

		complex(double real, double imaginary) {
			this->real = real;
			this->imaginary = imaginary;
		}

		// copy constructor
		complex(const complex &old_complex) {
			real = old_complex.real;
			imaginary = old_complex.imaginary;
		}

		double realpart() {
			return real;
		}

		double imaginarypart() {
			return imaginary;
		}

		~complex() {}

		void set(double real, double imaginary) {
			this->real = real;
			this->imaginary = imaginary;
		}

		void set_real(double real) {
			this->real = real;
		}

		void set_imaginary(double imaginary) {
			this->imaginary = imaginary;
		}

		// operator overloading
		complex operator = (const complex &oper2) {
			set(oper2.real, oper2.imaginary);
			return *this;
		}

		complex &operator += (const complex &oper2) {
			real += oper2.real;
			imaginary += oper2.imaginary;
			return *this;
		}

		complex &operator += (double oper2) {
			real += oper2;
			return *this;
		}

		complex &operator -= (const complex &oper2) {
			real -= oper2.real;
			imaginary -= oper2.imaginary;
			return *this;
		}

		complex &operator -= (double oper2) {
			real -= oper2;
			return *this;
		}

		complex &operator *= (const complex &oper2) {
			real = real * oper2.real - imaginary * oper2.imaginary;
			imaginary = real * oper2.imaginary + imaginary * oper2.real;
			return *this;
		}

		complex &operator *= (double oper2) {
			real *= oper2;
			imaginary *= oper2;
			return *this;
		}

		/*
		complex &operator /= (const complex &oper2) {
			complex denominator(*this * oper2);
			denominator /= ( oper2.real * oper2.real - oper2.imaginary * oper2.imaginary);
			real = denominator.realpart();
			imaginary = denominator.imaginarypart();
			return *this;
		}
		*/

		complex &operator /= (double oper2) {
			real /= oper2;
			imaginary /= oper2;
			return *this;
		}
		
};

// binary arithmatic operators
inline complex operator + (const complex &oper1, const complex &oper2) {
	return complex(oper1.realpart() + oper2.realpart(),
			oper1.imaginarypart() + oper2.imaginarypart());
}

inline complex operator - (const complex &oper1, const complex &oper2) {
	return complex(oper1.realpart() - oper2.realpart(),
			oper1.imaginarypart() - oper2.imaginarypart());
}

inline complex operator * (const complex &oper1, const complex &oper2) {
	return complex(oper1.realpart() * oper2.realpart() - oper1.imaginarypart() * oper2.imaginarypart(),
			oper1.realpart() * oper2.imaginarypart() + oper1.imaginarypart() * oepr2.realpart());
}

/*
inline complex operator + (const complex &oper1, const complex &oper2) {
	return complex(oper1.realpart() + oper2.realpart(),
			oper1.imaginarypart() + oper2.imaginarypart());
}
*/


#endif // _complex_h_
