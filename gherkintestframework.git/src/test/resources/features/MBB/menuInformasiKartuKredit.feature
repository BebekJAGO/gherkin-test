Feature: Kartu Kredit

  @test0
  Scenario Outline: Menu Informasi Kartu Kredit given selalu ada login
    Given maker user logged in
    When the user access menu KK
    Then got error message

  @test0
    Scenario Outline: Menu Informasi Kartu Kredit
      Given user access menu KK
      When the user search "<Search Input>" grup rekening KK
      Then rekening yang masuk dalam grup rek tetap tampil

      Examples:
      | Search Input |
      | Tambah CC 123 |

  @test1
    Scenario Outline: Tabel Kartu Kredit
    Given user access menu KK
    When user sort cust number once
    Then customer number sorted ascending
