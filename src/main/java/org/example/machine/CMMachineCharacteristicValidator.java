package org.example.machine;

public class CMMachineCharacteristicValidator implements CharacteristicValidator{
    /*
   * p1 p12 p321 p2_ p32_ p342_ p2_1 p32_23 p342_33 p2_12 p32_23 p342_44 p2_12 p32_23 p342_44 p1_1_3 p13_32_88 p123_323_881 p1_A p23_AB P234_ABC p2_h22mm p22_h2mm p232_h2.52mm p1_ref_ABC p12_ref_DA p133_ref_ABC
   *
   ([pP]\d+(_\d+)*(_[A-Za-z0-9]+)?) - dopasowuje parametry zaczynające się od litery "p" lub "P", a
   następnie jednej lub więcej cyfr, z opcjonalnymi grupami podkreślenia i cyfr lub liter

    | - oznacza alternatywę, dopasuje alternatywne wzorce

    ([pP]\d+_[A-Za-z]+(_[A-Za-z0-9]+)?) - dopasowuje parametry zaczynające się od litery "p" lub "P", a następnie jednej lub więcej cyfr, po których następuje podkreślenie, a następnie jedna lub więcej liter, z opcjonalnymi grupami podkreślenia i cyfr lub liter
    | - oznacza alternatywę, dopasuje alternatywne wzorce
    ([pP][A-Za-z0-9]+_[A-Za-z0-9]+(_[A-Za-z0-9]+)*(_[A-Za-z]+)?) - dopasowuje parametry zaczynające się od litery "p" lub "P", a następnie jednej lub więcej liter lub cyfr, po których następuje podkreślenie, a następnie jedna lub więcej liter lub cyfr, z opcjonalnymi grupami podkreślenia i liter lub cyfr oraz z dodatkową grupą podkreślenia i liter na końcu
    * */
    private final String CMM_NAME_REGEX = "([pP]\\d+(_\\d+)*(_[A-Za-z0-9]+)?)|([pP]\\d+_[A-Za-z]+(_[A-Za-z0-9]+)?)|([pP][A-Za-z0-9]+_[A-Za-z0-9]+(_[A-Za-z0-9]+)*(_[A-Za-z]+)?)";

/*    ([pP]\d{1,2}(_\d{1,2})?(_[A-Za-z0-9]{1,6})?) - dopasowuje parametry zaczynające się od litery "p" lub "P", a następnie jednej lub dwóch cyfr, z opcjonalnymi grupami podkreślenia i jednej lub dwóch cyfr lub liter o długości do 6 znaków
    | - oznacza alternatywę, dopasuje alternatywne wzorce
    ([pP]\d{1,2}_[A-Za-z]{1,5}(_[A-Za-z0-9]{1,4})?) - dopasowuje parametry zaczynające się od litery "p" lub "P", a następnie jednej lub dwóch cyfr, po których następuje podkreślenie, a następnie jedna lub więcej liter o długości do 5 znaków, z opcjonalnymi grupami podkreślenia i jednej lub więcej cyfr lub liter o długości do 4 znaków
    | - oznacza alternatywę, dopasuje alternatywne wzorce
    ([pP][A-Za-z0-9]{1,4}_[A-Za-z0-9]{1,4}(_[A-Za-z0-9]{1,4})?(_[A-Za-z]{1,2})?) - dopasowuje parametry zaczynające się od litery "p" lub "P", a następnie jednej lub więcej liter lub cyfr o długości do 4 znaków, po których następuje podkreślenie, a następnie jedna lub więcej liter lub cyfr o długości do 4 znaków, z opcjonalnymi grupami podkreślenia i liter lub cyfr o długości do 4 znaków oraz z dodatkową grupą podkreślenia i liter o długości do 2 znaków na końcu.*/

    /*
    *  p1, p15, P234, P_1, p_234, p234_, P12_1_3, p13_23_3, P6_  , p12_ , P3_23
        \b oznacza granicę słowa, dzięki temu pasujące wyrażenie musi znajdować się pomiędzy granicami słowa, np. nie dopasuje do "xp123"
    [pP] oznacza, że wyrażenie zaczyna się od litery "p" lub "P"
    \d{1,3} oznacza, że po literze "p" może występować od 1 do 3 cyfr
    (_\d{1,3})* oznacza, że po cyfrach może wystąpić zero lub więcej wystąpień podkreślenia "_" i kolejnych od 1 do 3 cyfr
    \b zamyka granice słowa
    * */
    private final String NAME_REGEX_1 ="\b[pP]\\d{1,3}(_\\d{1,3})*\\b";


    // p1_h12.4mm, p12_AB , P1.1
    /*
    Wyjaśnienie:

    ^ oznacza początek ciągu znaków, co oznacza, że wyrażenie musi zaczynać się od opisywanego parametru
    [pP] oznacza, że wyrażenie musi zaczynać się od litery "p" lub "P"
    \d+ oznacza, że po literze "p" może wystąpić jeden lub więcej cyfr
    (\.\d+)? oznacza, że może wystąpić opcjonalnie kropka "." i jeden lub więcej cyfr
    (_[a-zA-Z0-9]+)? oznacza, że po cyfrach lub poprzednim podkreśleniu może wystąpić opcjonalnie podkreślenie "_" i ciąg liter lub cyfr
    (_h\d+(\.\d+)?mm)? oznacza, że po cyfrach lub poprzednim podkreśleniu i literach może wystąpić opcjonalnie podkreślenie "_" i litera "h", a następnie ciąg cyfr, opcjonalnie poprzedzony kropką "." i kolejnymi cyframi, a następnie literami "mm"
    $ oznacza koniec ciągu znaków, co oznacza, że wyrażenie musi kończyć się na opisywanym parametrze

    Zauważ, że wyrażenie regularne uwzględnia przypadki, w których po literze "p" występują tylko cyfry, ewentualnie poprzedzone kropką i cyframi, a następnie mogą wystąpić opcjonalnie podkreślenie i ciąg liter i cyfr, a także przypadki, w których po literze "P" występują tylko cyfry oddzielone kropką. Dodatkowo uwzględniono możliwość wystąpienia liter "h" i cyfr oddzielonych kropką i ewentualnie poprzedzonego podkreśleniem.
    * */
    private final String NAME_REGEX_2 = "^[pP]\\d+(\\.\\d+)?(_[a-zA-Z0-9]+)?(_h\\d+(\\.\\d+)?mm)?$";


    private final String NAME_REGEX_3 = "\\b[Pp]\\d{1,3}(_\\d{1,3})*(_[a-zA-Z0-9]+)*(_h\\d+(\\.\\d+)?mm)?\\b\n";


    /* P lub p
    ^ oznacza początek ciągu znaków, co oznacza, że wyrażenie musi zaczynać się od opisywanego parametru
    [pP] oznacza, że wyrażenie musi zaczynać się od litery "p" lub "P"
    ([0-9]+)? oznacza, że po cyfrach lub poprzednim podkreśleniu może wystąpić opcjonalnie ciąg cyfr
    (_[a-zA-Z0-9]+)? oznacza, że po cyfrach lub poprzednim podkreśleniu może wystąpić opcjonalnie podkreślenie "_" i ciąg liter lub cyfr
    (\\.[a-zA-Z0-9]+)? oznacza, że po cyfrach lub poprzednim podkreśleniu może wystąpić opcjonalnie kropka"." i ciąg liter lub cyfr
     */

    private final String NAME_REGEX_4 ="^[pP](_[a-zA-Z0-9]+)(\\.[a-zA-Z0-9]+)?";  //działa częściowo

    /*
    P_12_234_1^1 ,P_12 , P_12_1 , p_1, p2_1^1

    ^ oznacza początek ciągu
    [pP] oznacza, że wyrażenie musi zaczynać się na P lub P
    _?\.?^? oznacza, że 0 lib więcej _ . ^
    [0-9]+ oznacza 1 lub wiecej
    [0-9]* oznacza 0 lub wiecej
    * */
    private final String NAME_REGEX_5 ="^[pP]_?\\.?^?[0-9]+_?\\.?\\^?[0-9]*_?\\.?\\^?[0-9]*";

    private void regex(){

        StringBuilder regex = new StringBuilder();



    }
    @Override
    public String getStringCharacteristicNameRegex() {
        return NAME_REGEX_5;
    }
}
