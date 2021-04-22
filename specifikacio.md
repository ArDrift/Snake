# Objektumorientált programozás HF specifikáció

> Snake (Kígyójáték) - Schelb Arnold (S761AX)

## A feladat rövid leírása

A Snake (Kígyó) egy klasszikus videójáték, amely egyszerű szabályai és felülete miatt széles körben ismert.

A játék célja, hogy a játékos által irányított kígyó minél hosszabb legyen, amit úgy lehet elérni, hogy felvesszük vele a pályára véletlenszerűen lehelyezett almát. Ilyenkor a kígyó megnő, és újabb alma kerül a pályára.

A kígyót a pályán négyféle irányba (fel, le, jobbra, balra) lehet mozgatni, mégpedig mezőnként, amikre a pálya is fel van osztva. Játék közben a kígyó folyamatosan mozgásban van, és amekkora a hossza, akkora nyomot hagy maga után, ebbe fordulhat bele saját maga is.

A játékosnak arra kell figyelnie, hogy a kígyó ne ütközzön se a pálya szélébe, se önmagába, mivel olyankor a játék véget ér.

A fokozatos nehezedésért a kígyó növekedése felel, mivel az minél nagyobb, annál több területet foglal el a pályából, így egyre nehezebb lesz a játékosnak úgy irányítania, hogy ne ütközzön semmibe. A játékos pontszáma az almák megszerzésével emelkedik.

## Funkcionalitás bemutatása

A program indításakor parancssoros menü fogadja a felhasználót, amiben billentyűkkel (nyilak, Enter, Escape) navigálhat. Itt indítható új játék, tölthető be a dicsőséglista az eddig elért 5 legmagasabb pontszámmal és a hozzájuk tartozó névvel, állítható be a pálya mérete, tervezhető egyedi pálya, vagy zárható be a program.

Új játék indításánál az Escape gombbal megállítható a játék, onnan elmenthető az aktuális állapot, folytathatjuk a játékot, vagy kiléphetünk belőle.

Játék közben pedig a fel, le, jobbra, balra nyilakkal mozgatható a kígyó.

Ha a játék végén elért pontszámunk a legjobb 5 közé esik, akkor a program felkínálja a dicsőséglistára mentés lehetőségét, amihez a felhasználó megadhat egy nevet. A dicsőséglista megtekintésnél rendezhető, név, pontszám, vagy dátum szerint.

## Megvalósítás

A program a beállított pályaméretnek megfelelő 2 dimenziós listát (mátrixot) tárol, mindig az éppen aktuális állapotában, mentésnél pedig ezt képes kiírni fájlba, a jelenlegi pontszámmal együtt. Ebben a listában karakterekkel különböztetjük meg a kígyó testét, fejét (és annak irányát), az almát és az üres mezőket.

Mentésnél az első szekció (üres sorig) a pályát tartalmazza, ezután pedig a mentéshez tartozó pontszám található. A dicsőséglistában név, pontszám, és dátum szerepel egymás alatt, a lista elemeit szintén üres sor választja el egymástól.

A játék megjelenítéséhez a terminál adott pozícióira ír karaktereket a program, ilyen módon kizárólag parancssoros felületű.
