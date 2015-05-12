Feature: search

  Scenario: Search pc
  Open "pc_search" page
  Click "computers"
  Click "notebook"
  See "2826" in "model_number"

  Scenario: Search pc fail
  Open "pc_search" page
  Click "computers"
  Click "notebook"
  See "2826" in "model_number"