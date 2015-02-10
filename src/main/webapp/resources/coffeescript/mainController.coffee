$(document).ready ->
  $("#get-trade-analysis-btn").click ->
    $.ajax 'trade/'+$('#tradeid').val(),
      type: 'GET',
      success: (res, status, xhr) ->
        alert 'success'
        0
      error: (res, status, xhr) ->
        alert 'error'
        0