# SpolujízdaNaPožádání

> Zadání projektu WAB, xjezek, xhvozdov, xboba

Bezplatná komunitní spolujízda „na požádání“: řidič, který už jede, nabídne volná místa, a někdo, kdo potřebuje jet stejným směrem v nejbližší hodině, se k němu připojí jedním klikem. Bez plateb, jen sdílení cesty, s důrazem na bezpečnost.

## Problém a uživatelé

**Problém.** Spousta lidí jezdí městem a regionem sama v autě, zatímco jiní stejnou cestu řeší MHD s přestupy nebo drahým taxíkem. Existující služby nepomáhají:

- **BlaBlaCar, Hopin** míří na dlouhé trasy plánované s předstihem.
- **Uber, Bolt, Grab** jsou placené taxislužby.
- **Facebook skupiny a WhatsApp** jsou nestrukturované, bez ověření identity a bez jakékoli ochrany při problému.

**Uživatelé.**

| Role           | Co dnes dělá                       | Co chce                                        |
| -------------- | ---------------------------------- | ---------------------------------------------- |
| Řidič          | Jezdí sám, auto je poloprázdné     | Vzít cestou někoho bez zajížďky a bez starostí |
| Spolucestující | Čeká na MHD, platí taxi, nemá auto | Rychle se dostat z A do B, bezpečně a zdarma   |

**Pro koho v prvním nasazení.** Jedna uzavřená komunita (např. studenti a zaměstnanci Mendelu) a jedna typická trasa. Tím řešíme problém „prázdného marketplace“ a zjednodušujeme ověřování.

## Hlavní scénáře

1. **Řidič zveřejní jízdu.** Zadá start, cíl, čas odjezdu a počet volných míst. Systém spočítá trasu a uloží ji.
2. **Spolucestující zadá požadavek.** Zadá odkud, kam a časové okno. Systém vrátí řidiče, jejichž trasa vede kolem a zajížďka je v limitu (např. do 5 minut).
3. **Domluva a vyzvednutí.** Řidič jedním klikem přijme. Oba vidí jméno, fotku, SPZ a místo setkání. Při vyzvednutí si potvrdí jednorázový kód, aby nenastoupil do špatného auta.
4. **Jízda a dokončení.** Po dobu jízdy je živá poloha viditelná jen účastníkům a určenému důvěryhodnému kontaktu. K dispozici je SOS tlačítko. Po dojezdu následuje vzájemné hodnocení.

## Bezpečnost a ochrana proti zneužití

| Riziko                     | Opatření                                                                                             |
| -------------------------- | ---------------------------------------------------------------------------------------------------- |
| Falešné identity           | Registrace jen přes ověřený školní/firemní e-mail, ověření telefonu, u řidiče evidence vozidla (SPZ) |
| Neznámý řidič nebo pasažér | Veřejný profil: počet jízd, hodnocení, historie nahlášení                                            |
| Nástup do špatného auta    | Jednorázový kód potvrzený při vyzvednutí                                                             |
| Něco se stane za jízdy     | Sdílení polohy s důvěryhodným kontaktem, SOS tlačítko, záznam průběhu jízdy                          |
| Stalking a únik polohy     | Přesné adresy se nezobrazují, jen místa setkání. Poloha viditelná jen účastníkům. Po jízdě se maže   |
| Spam a trolling            | Rate limity, blokování uživatelů, automatický ban po opakovaných nahlášeních                         |

„Veřejné“ znamená transparentní (profily, hodnocení, pravidla), ne veřejně viditelná poloha.

### Microservisy

| Služba               | Odpovědnost                                                 |                          |
| -------------------- | ----------------------------------------------------------- | ------------------------ |
| **Uživatelé**        | Registrace, ověření, profily, hodnocení, nahlášení, blokace | Firebase, auth           |
| **Jízdy**            | Nabídky, požadavky, životní cyklus jízdy                    | Vlastní datový model     |
| **Párování**         | Geo a časový výpočet: které nabídky odpovídají požadavku    |                          |
| **Sledování polohy** | Příjem poloh, živé sdílení, SOS                             | WebSocket (Socket.IO)    |
| **Notifikace**       | Push, e-mail, SMS (SOS)                                     | Firebase Cloud Messaging |

## Rozsah
****

- registrace s ověřením e-mailu a telefonu, profil, vozidlo,
- nabídka jízdy a požadavek na jízdu,
- párování podle koridoru a časového okna,
- přijetí/odmítnutí, jednorázový kód při vyzvednutí,
- sdílení živé polohy s důvěryhodným kontaktem, SOS,
- hodnocení a nahlášení uživatele,
- responzivní web.
