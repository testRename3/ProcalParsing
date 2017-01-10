package fx50.CalcMath;

import org.nevec.rjm.BigDecimalMath;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Constants
 */
public enum Constants {
    pi          (BigDecimalMath.pi(new MathContext(20)),
            "pi","-"),
    exp         (BigDecimalMath.exp(new MathContext(20)),
            "Euler's number","-"),
    m_p         (new BigDecimal("1.67262171E-27"),
            "Proton mass","kg"),
    m_n         (new BigDecimal("1.67492728E-27"),
            "Neutron mass","kg"),
    m_e         (new BigDecimal("9.1093826E-31"),
            "Electron mass","kg"),
    m_mu        (new BigDecimal("1.8835314E-28"),
            "Muon mass","kg"),
    a_0         (new BigDecimal("0.5291772108E-10"),
            "Bohr radius","m"),
    h           (new BigDecimal("6.6260693E-34"),
            "Planck constant","Js"),
    mu_N        (new BigDecimal("5.05078343E-27"),
            "Nuclear magneton","J/T"),
    mu_B        (new BigDecimal("927.400949E-26"),
            "Bohr magneton","J/T"),
    h_stroke    (new BigDecimal("1.05457168E-34"),
            "Planck constant, rationalized","Js"),
    alpha       (new BigDecimal("7.297352568E-3"),
            "Fine-structure constant","-"),
    r_e         (new BigDecimal("2.817940325E-15"),
            "Classical electron radius","m"),
    lambda_c    (new BigDecimal("2.426310238E-12"),
            "Compton wavelength","m"),
    gamma_p     (new BigDecimal("2.67522205E8"),
            "Proton gyromagnetic ratio","1/s/T"),
    lambda_cp   (new BigDecimal("1.3214098555E-15"),
            "Proton Compton wavelength","m"),
    lambda_cn   (new BigDecimal("1.3195909067E-15"),
            "Neutron Compton wavelength","m"),
    R_inf       (new BigDecimal("10973931.568525"),
            "Rydberg constant","1/m"),
    u           (new BigDecimal("1.66053886E-27"),
            "Atomic mass constant","kg"),
    mu_p        (new BigDecimal("1.41060671E-26"),
            "Proton magnetic moment","J/T"),
    mu_e        (new BigDecimal("-928.476412E-26"),
            "Electron magnetic moment","J/T"),
    mu_n        (new BigDecimal("-0.96623645E-26"),
            "Neutron magnetic moment","J/T"),
    mu_mu       (new BigDecimal("-4.49044799E-26"),
            "Muon magnetic moment","J/T"),
    F           (new BigDecimal("96485.3383"),
            "Faraday constant","C/mol"),
    e           (new BigDecimal("1.60217653E-19"),
            "Elementary charge","C"),
    N_A         (new BigDecimal("1.60217653E-19"),
            "Avogadro constant","1/mol"),
    k           (new BigDecimal("1.3806505E-23"),
            "Boltzmann constant","J/K"),
    V_m         (new BigDecimal("22.413996E-3"),
            "Molar volume of ideal gas","m^3/mol"),
    R           (new BigDecimal("8.314472"),
            "Molar gas constant","J/mol/K"),
    c_0         (new BigDecimal("299792458"),
            "Speed of light in vacuum","m/s"),
    c_1         (new BigDecimal("3.74177138E-16"),
            "First radiation constant","Wm^2"),
    c_2         (new BigDecimal("1.4387752E-2"),
            "Second radiation constant","mK"),
    sigma       (new BigDecimal("5.670400E-8"),
            "Stefan-Boltzmann constant","Wm^2/K^4"),
    epsilon_0   (new BigDecimal("8.854187817E-12"),
            "Electric constant","F/m"),
    mu_0        (new BigDecimal("12.566370614E-7"),
            "Magnetic constant","N/A^2"),
    phi_0       (new BigDecimal("2.06783372E-15"),
            "Magnetic flux quantum","Wb"),
    g           (new BigDecimal("9.80665"),
            "Standard acceleration of gravity","m/s^2"),
    G_0         (new BigDecimal("7.748091733E-5"),
            "Conductance quantum","S"),
    Z_0         (new BigDecimal("376.730313461"),
            "Characteristic impedance of vacuum","Omega"),
    t           (new BigDecimal("273.15"),
            "Celsius temperature","K"),
    G           (new BigDecimal("6.6742E-11"),
            "Newtonian constant of gravity","m^3/kg/s^2"),
    atm         (new BigDecimal("101325"),
            "Standard atmosphere","Pa");

    public BigDecimal value;
    public String name;
    public String unit;

    Constants(BigDecimal value, String name, String unit) {
        this.value = value;
        this.name = name;
        this.unit = unit;
    }

    public BigDecimal getValue() {
        return value;
    }
}
