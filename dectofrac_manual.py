from decimal import *
getcontext().prec = 100
accuracy = 7 # Pending to resolve
brutethreshold = 13

digits = []
digit = 0

def inversion(decimal): # Invert the fraction and consider only the frac part
    global digits
    if decimal != 0:
        decimal = Decimal('1') / decimal
        digits.append(int(decimal))
        if round(decimal, accuracy) != int(decimal):
            #print(decimal)
            decimal -= int(decimal)
            return inversion(decimal)

def bruteconv(digits): # Revert the inversion by doing frac + -
    global denominator, numerator
    denominator = digits.pop()
    numerator = 1
    while len(digits) != 0:
        temp = numerator + denominator * digits.pop()
        numerator = denominator
        denominator = temp

def countsf(decimal): # Count sigificant figures
    return -Decimal(decimal).as_tuple().exponent
    #return len(Decimal(str(float(decimal))).as_tuple().digits)

def hcf(a, b): # Find HCF
    while b != 0:
        t = b
        b = a%b
        a = t
    return a

def brute(decimal): # Brute force main
    global digits, digit, denominator, numerator
    digit = int(decimal)
    if digit != 0:
        digits.append(0)
        digits.append(digit)
        decimal -= digit
    inversion(decimal)
    #print(digits)
    bruteconv(digits)
    return numerator, denominator

def natural(decimal): # Natural conversion main
    exp = -decimal.as_tuple().exponent
    numerator = int(decimal * 10**exp)
    denominator = 10**(exp)
    factor = hcf(numerator, denominator)
    return numerator//factor, denominator//factor

while True: # I/O
    decimal = input("Input a decimal: ")
    if countsf(decimal) >= brutethreshold:
        print(brute(Decimal(decimal)))
    else:
        print(natural(Decimal(decimal)))
    digits = []
